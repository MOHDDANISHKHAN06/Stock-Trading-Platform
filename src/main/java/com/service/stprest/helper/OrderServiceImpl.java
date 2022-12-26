package com.service.stprest.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.stprest.dao.OrderDao;
import com.service.stprest.dao.StockDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.Order;



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
		double buyingPower = walletDao.getReferenceById(order.getUserOrderId().getEmailId()).getBuyingPower();
		
		if(orderValue <= buyingPower )
		{
			Util.orderQueue.add(order);
		}
		else {
			throw new RuntimeException("Order value is greater than buying power");
		}
		
	}

}
