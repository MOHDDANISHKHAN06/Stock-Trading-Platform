package com.service.stprest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
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
				
				//Date now = new Date();
//				if(order.getExpiry().after(now))
//				{
//					order.setStatus("Order Expired");
//					cancelOrder(order);
//				}	
				if(order.getOrderType().equals("BUY"))
					buyStock(order);
				else 
					sellStock(order);
				// check returned status and accordingly add to queue or change status

			}	
			try {
				// sleep for 1 sec
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void cancelOrder(Order order)
	{
		//cancel limit-order if user wants to cancel it or it is expired; 
		orderDao.save(order);
	}
	
	public void buyStock(Order order)
	{
						
		//Place order
		placeOrder(order);
		//return status

	}
	
	public void sellStock(Order order)
	{
		//Place order
		placeOrder(order);
		//return status
		
	}
	
	@Transactional
	public void placeOrder(Order order)
	{
		//update stocks
		updateStocks(order);

		//update wallet
		updateWallet(order);
		
		//update user_stocks
		updateUserStocks(order);
		
		//Place order
		orderDao.save(order);
		
		//if all successful proceed else revert back the changes and add order back to queue
		//return status
	}
	
	public void updateStocks(Order order)
	{
		Stock stock = stockDao.findById(order.getTicker()).get();
		long companyVolume = stock.getVolume();
		long newVolume = companyVolume + order.getNumOfShares();
		stock.setVolume(newVolume);
		stockDao.save(stock);
	}

	public void updateWallet(Order order)
	{
		Wallet wallet = walletDao.findById(order.getUserOrderId().getEmailId()).get();
		double cashAvailable = wallet.getCashAvailable();
		double currentPrice = stockDao.findById(order.getTicker()).get().getCurrentPrice();
		double totalOrderAmount = currentPrice * order.getNumOfShares();

		double newCashAmount = (order.getOrderType().equals("BUY"))? cashAvailable - totalOrderAmount : cashAvailable + totalOrderAmount;
		wallet.setCashAvailable(newCashAmount);
		walletDao.save(wallet);
	}
	
	public void updateUserStocks(Order order)
	{
		UserStockId userStockId = new UserStockId();
		userStockId.setEmailId(order.getUserOrderId().getEmailId());
		userStockId.setTicker(order.getTicker());
		long existingShares = 0;
		UserStocks userStock;
		if(userStockDao.existsById(userStockId)) {
			userStock = userStockDao.findById(userStockId).get();
			existingShares = userStock.getNumOfShares();
		}
		else{
			userStock = new UserStocks();
			userStock.setUser(this.userService.getUser(order.getUserOrderId().getEmailId()));
		}
		
		long newShares = order.getNumOfShares();			
		long updatedNumOfShares = (order.getOrderType().equals("BUY"))? existingShares + newShares : existingShares - newShares;
		userStock.setNumOfShares(updatedNumOfShares);
		userStockDao.save(userStock);
			
	}
	
	

}
