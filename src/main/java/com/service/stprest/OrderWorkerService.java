package com.service.stprest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.service.stprest.dao.OrderDao;
import com.service.stprest.dao.StockDao;
import com.service.stprest.dao.UserStockDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.Order;
import com.service.stprest.entities.Stock;
import com.service.stprest.entities.UserStockId;
import com.service.stprest.entities.UserStocks;
import com.service.stprest.entities.Wallet;
import com.service.stprest.helper.UserService;
import com.service.stprest.helper.Util;

@Component
@Scope("prototype")
@EnableTransactionManagement
public class OrderWorkerService implements Runnable {
	
	@Autowired
	private StockDao stockDao;
	@Autowired
	private WalletDao walletDao; 
	@Autowired
	private UserStockDao userStockDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	UserService userService;
	
	@Override
	public void run() {
		// long running back end service
		while(true) {
			Order order = Util.orderQueue.poll();
			if(order != null) {
				if(orderDao.findById(order.getOrderId()).get().getStatus().equals("Cancelled")){
					continue;
				}
				if(order.getLimitValue() !=0 ){
					double currentPrice = stockDao.findById(order.getTicker()).get().getCurrentPrice();
					if(order.getOrderType().equals("BUY") && order.getLimitValue() > currentPrice )
						Util.orderQueue.add(order);
					if(order.getOrderType().equals("SELL") && order.getLimitValue() < currentPrice )
						Util.orderQueue.add(order);
					continue;
				}
				///Handling expired orders
				LocalDate now = LocalDate.now();
				if(order.getExpiry().isBefore(now))
				{
					order.setStatus("Order Expired");
					orderDao.save(order);
					continue;
				}	
				placeOrder(order);			
			}	
			try {
				// sleep for 1 sec
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Transactional
	public void placeOrder(Order order)
	{
		updateStocks(order);
		updateWallet(order);		
		updateUserStocks(order);
		
		//Place order
		order.setUser(this.userService.getUser(order.getEmailId()));
		order.setStatus("Order is Successfull");
		orderDao.save(order);
		
		//if all successful proceed else revert back the changes and add order back to queue
		//return status
	}
	
	public void updateStocks(Order order)
	{
		Stock stock = stockDao.findById(order.getTicker()).get();
		long companyVolume = stock.getVolume();
		long newVolume = (order.getOrderType().equals("BUY"))? companyVolume + order.getNumOfShares() : companyVolume - order.getNumOfShares();;
		stock.setVolume(newVolume);
		stockDao.save(stock);
	}

	public void updateWallet(Order order)
	{
		Wallet wallet = walletDao.findById(order.getEmailId()).get();
		double cashAvailable = wallet.getCashAvailable();
		double currentPrice = stockDao.findById(order.getTicker()).get().getCurrentPrice();
		double totalOrderAmount = currentPrice * order.getNumOfShares();
		double newCashAmount;
		if(order.getOrderType().equals("SELL")) {
			 wallet.setBuyingPower(wallet.getBuyingPower() + totalOrderAmount);
			 newCashAmount = cashAvailable + totalOrderAmount;
		}
		else {
			  newCashAmount = cashAvailable - totalOrderAmount;
		}
		wallet.setCashAvailable(newCashAmount);
		walletDao.save(wallet);
	}
	
	public void updateUserStocks(Order order)
	{
		UserStockId userStockId = new UserStockId();
		userStockId.setEmailId(order.getEmailId());
		userStockId.setTicker(order.getTicker());
		long existingShares = 0;
		UserStocks userStock;
		if(userStockDao.existsById(userStockId)) {
			userStock = userStockDao.findById(userStockId).get();
			existingShares = userStock.getNumOfShares();
		}
		else{
			userStock = new UserStocks();
			userStock.setId(userStockId);
			userStock.setUser(this.userService.getUser(order.getEmailId()));
		}
		
		long newShares = order.getNumOfShares();			
		long updatedNumOfShares = (order.getOrderType().equals("BUY"))? existingShares + newShares : existingShares - newShares;
		userStock.setNumOfShares(updatedNumOfShares);
		userStockDao.save(userStock);
			
	}
	
	

}
