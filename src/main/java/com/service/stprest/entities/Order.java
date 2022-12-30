package com.service.stprest.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@SequenceGenerator(name="seq", allocationSize=100)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")    
	private int orderId = 1;
	private long limitValue;
	private LocalDate expiry;
	private String orderType;
	private String status;
	private long numOfShares;
	private String ticker;
	
	//do we need emailId, every user will have a email id , we can get it from there??
	private String emailId;

	public int getOrderId() {
		return orderId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@ManyToOne
	@MapsId("emailId")
	private User user;


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

		public LocalDate getExpiry() {
			return expiry;
		}
	
		public void setExpiry(LocalDate expiry) {
			
			this.expiry = expiry;
		}

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
