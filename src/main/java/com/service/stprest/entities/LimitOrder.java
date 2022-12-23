package com.service.stprest.entities;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class LimitOrder {
	
	//cancel
	//update transaction
	
	private long limitValue;
	private Date expiry;
	private String orderType;
	private boolean orderPlaced;
	
	public boolean isOrderPlaced() {
		return orderPlaced;
	}
	public void setOrderPlaced(boolean orderPlaced) {
		this.orderPlaced = orderPlaced;
	}
	public long getLimitValue() {
		return limitValue;
	}
	public void setLimitValue(long limitValue) {
		this.limitValue = limitValue;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public LimitOrder(long limitValue, Date expiry, String orderType, boolean orderPlaced) {
		super();
		this.limitValue = limitValue;
		this.expiry = expiry;
		this.orderType = orderType;
		this.orderPlaced = orderPlaced;
	}
}
