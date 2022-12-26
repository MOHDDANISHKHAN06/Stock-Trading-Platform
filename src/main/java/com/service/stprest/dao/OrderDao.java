package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.stprest.entities.Order;
import com.service.stprest.entities.UserOrderId;

public interface OrderDao extends JpaRepository<Order, UserOrderId> {

}
