package com.service.stprest.helper;

import java.util.List;

import com.service.stprest.entities.Order;

public interface OrderService {

	public List<Order> getorders();

	public void addOrder(Order order);
	
	public void cancelOrder(int orderId);

	public List<Order> getOrders(String emailId);

	public List<Order> getOrdersInProgress(String emailId);
}
