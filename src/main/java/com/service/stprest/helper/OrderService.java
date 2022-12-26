package com.service.stprest.helper;

import java.util.List;

import com.service.stprest.entities.Order;

public interface OrderService {

	public List<Order> getorders();

	public void addOrder(Order order);
	
}
