package com.service.stprest.helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.stprest.dao.MarketScheduleDao;
import com.service.stprest.dao.OrderDao;
import com.service.stprest.dao.StockDao;
import com.service.stprest.dao.UserStockDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.MarketSchedule;
import com.service.stprest.entities.Order;
import com.service.stprest.entities.Transactions;
import com.service.stprest.entities.UserStockId;
import com.service.stprest.entities.Wallet;



@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private StockDao stockDao;
	@Autowired
	private WalletDao walletDao;
	@Autowired
	private UserStockDao userStockDao;
	@Autowired
	private MarketScheduleDao marketScheduleDao;
	
	@Override
	public List<Order> getorders() {
			return orderDao.findAll();
	}
	@Override
	public void addOrder(Order order) {
		
		MarketSchedule marketSchedule = marketScheduleDao.findById(1).get();
		// Check if order is in market hours before executing it
		if(!MarketUtil.isValidMarketHour(marketSchedule.getHolidays(), marketSchedule.getStartTime(), marketSchedule.getEndTime(), LocalDate.now(), LocalTime.now())) {
			throw new RuntimeException("Order cannot be placed right now: market is closed ");
		}
		
		double orderValue = order.getNumOfShares() * stockDao.getReferenceById(order.getTicker()).getCurrentPrice();
		double buyingPower = walletDao.getReferenceById(order.getEmailId()).getBuyingPower();
		if(order.getOrderType().equals("SELL"))
		{
			UserStockId userStockId = new UserStockId();
			userStockId.setEmailId(order.getEmailId());
			userStockId.setTicker(order.getTicker());
			long existingShares = userStockDao.findById(userStockId).get().getNumOfShares();
			if(existingShares >= order.getNumOfShares())
			{
				order.setStatus("In Progress");
				orderDao.save(order);
				Util.orderQueue.add(order);	
			}
			else {
				order.setStatus("Invalid Sell");
				orderDao.save(order);
				throw new RuntimeException("Number of shares requested to sell is Invalid");
			}
		}
		else{
			long volume = stockDao.findById(order.getTicker()).get().getVolume();
			double mktcapitlisation = stockDao.findById(order.getTicker()).get().getMarketCapitalisation();
			
			if(mktcapitlisation-volume < order.getNumOfShares())
			{
				order.setStatus("Invalid Buy");
				orderDao.save(order);
				throw new RuntimeException("Number of shares requested to buy is Invalid");
			}
			if(orderValue <= buyingPower ){
				buyingPower-=orderValue;
				Wallet wallet = walletDao.findById(order.getEmailId()).get();
				wallet.setBuyingPower(buyingPower);
				walletDao.save(wallet);
				order.setStatus("In Progress");
				orderDao.save(order);
				Util.orderQueue.add(order);				
			}
			else {
				throw new RuntimeException("Order value is greater than buying power");
				}
		}
	}
	@Override
	public void cancelOrder(int orderId) {
		Order order = orderDao.findById(orderId).get();
		order.setStatus("Cancelled");
		orderDao.save(order);
	}
	@Override
	public List<Order> getOrdersInProgress(String emailId) {
		List<Order> lists = new ArrayList<>();
		try {
			lists = orderDao.findByEmailIdAndStatus(emailId, "In Progress");
		} catch (Exception e) {
			System.out.println("print");
		}
		return lists;

	}
	@Override
	public List<Order> getOrders(String emailId) {
		List<Order> lists = new ArrayList<>();
		try {
			lists = orderDao.findByEmailId(emailId);
		} catch (Exception e) {
			System.out.println("print");
		}
		return lists;
	}
}
