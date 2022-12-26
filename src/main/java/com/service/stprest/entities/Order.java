package com.service.stprest.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@EmbeddedId
	private UserOrderId userOrderId;
	private long limitValue;
	//private Date expiry;
	private String orderType;
	private String status;
	private long numOfShares;
	private String ticker;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@ManyToOne
	@MapsId("emailId")
	private User user;

	public UserOrderId getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(UserOrderId userOrderId) {
		this.userOrderId = userOrderId;
	}

	public long getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(long numOfShares) {
		this.numOfShares = numOfShares;
	}


	public long getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(long limitValue) {
		this.limitValue = limitValue;
	}

	//	public Date getExpiry() {
	//		return expiry;
	//	}
	//
	//	public void setExpiry(Date expiry) {
	//		this.expiry = expiry;
	//	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
