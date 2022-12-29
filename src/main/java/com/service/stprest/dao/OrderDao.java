package com.service.stprest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.service.stprest.entities.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

	
	List<Order> findByEmailIdAndStatus(String emailId, String status);

	List<Order> findByEmailId(String emailId);
}
