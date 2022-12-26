package com.service.stprest.entities;

//import java.sql.Time;
//import java.util.Date;

import jakarta.persistence.Entity;

//@Entity
public class Transactions {
	
	private UserTransactionId userTransactionId;
	private String transactionType;
	private double transactionAmt;
	
	//private Date date;
	//private Time time;
	
	public UserTransactionId getUserTransactionId() {
		return userTransactionId;
	}
	public void setUserTransactionId(UserTransactionId userTransactionId) {
		this.userTransactionId = userTransactionId;
	}

	public String getTransacnType() {
		return transactionType;
	}
	public void setTransacnType(String transacnType) {
		this.transactionType = transacnType;
	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public Time getTime() {
//		return time;
//	}
//	public void setTime(Time time) {
//		this.time = time;
//	}
	public double getTransacnAmt() {
		return transactionAmt;
	}
	public void setTransacnAmt(double transacnAmt) {
		this.transactionAmt = transacnAmt;
	}	
}
