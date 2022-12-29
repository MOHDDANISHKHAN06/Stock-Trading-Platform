package com.service.stprest.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.stprest.dao.OrderDao;
import com.service.stprest.dao.StockDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.Order;
import com.service.stprest.entities.Transactions;
import com.service.stprest.entities.Wallet;



@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private StockDao stockDao;
	@Autowired
	private WalletDao walletDao;
	
	@Override
	public List<Order> getorders() {
			return orderDao.findAll();
	}
	@Override
	public void addOrder(Order order) {
		double orderValue = order.getNumOfShares() * stockDao.getReferenceById(order.getTicker()).getCurrentPrice();
		double buyingPower = walletDao.getReferenceById(order.getEmailId()).getBuyingPower();
		if(order.getOrderType().equals("SELL"))
		{
			order.setStatus("In Progress");
			orderDao.save(order);
			Util.orderQueue.add(order);	
		}
		else{
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
