package com.service.stprest;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.service.stprest.dao.MarketScheduleDao;
import com.service.stprest.dao.OrderDao;
import com.service.stprest.dao.StockDao;
import com.service.stprest.dao.UserStockDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.MarketSchedule;
import com.service.stprest.entities.Order;
import com.service.stprest.entities.Stock;
import com.service.stprest.entities.UserStockId;
import com.service.stprest.entities.UserStocks;
import com.service.stprest.entities.Wallet;
import com.service.stprest.helper.MarketUtil;
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
	private MarketScheduleDao marketScheduleDao;
	@Autowired
	UserService userService;
	
	@Override
	public void run() {
		// long running back end service
		while(true) {
			synchronized (orderDao) {
				Order order = Util.orderQueue.poll();
				if(order != null) {
						
					if(orderDao.findById(order.getOrderId()).get().getStatus().equals("Cancelled")){
						continue;
					}
					
					///Handling expired orders'
					if (order.getExpiry()!= null && MarketUtil.isExpired(order)) {
						order.setStatus("Order Expired");
						orderDao.save(order);
						continue;
					}
					
					MarketSchedule marketSchedule = marketScheduleDao.findById(1).get();
					
					// Check if order is in market hours or not before executing it
					if(!MarketUtil.isValidMarketHour(marketSchedule.getHolidays(), marketSchedule.getStartTime(), marketSchedule.getEndTime(), LocalDate.now(), LocalTime.now())) {
						Util.orderQueue.add(order);
						try {
							// sleep for 1 sec
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}
					
					if(order.getLimitValue()!=0){
						double currentPrice = stockDao.findById(order.getTicker()).get().getCurrentPrice();
						if(order.getOrderType().equals("BUY") && order.getLimitValue() < currentPrice ) {
							Util.orderQueue.add(order);
							continue;
						}
						if(order.getOrderType().equals("SELL") && order.getLimitValue() > currentPrice ) {
							Util.orderQueue.add(order);
							continue;
						}
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
	}
	
	
	@Transactional
	public void placeOrder(Order order)
	{
		updateStocks(order);
		updateWallet(order);		
		updateUserStocks(order);
		
		//Place order
		order.setUser(this.userService.getUser(order.getEmailId()));
		order.setStatus("Completed");
		System.out.print(order.getOrderId());
		orderDao.save(order);
	}
	
	public void updateStocks(Order order)
	{
		Stock stock = stockDao.findById(order.getTicker()).get();
		long companyVolume = stock.getVolume();
		long newVolume = (order.getOrderType().equals("BUY"))? companyVolume + order.getNumOfShares() : companyVolume - order.getNumOfShares();;
		stock.setVolume(newVolume);
		stock.setMarketCapitalisation(newVolume*stock.getCurrentPrice());
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
