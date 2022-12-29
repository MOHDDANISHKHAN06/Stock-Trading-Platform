package com.service.stprest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Transactions {
	
	@Id
	@SequenceGenerator(name="seq", allocationSize=100)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")  
    private int transactionId; 
    private String emailId;
	private String transactionType;
	private double transactionAmount;
	//private Date date;
	//private Time time;
	
	public int getTransactionId() {
		return transactionId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransacnType(String transactionType) {
		this.transactionType = transactionType;
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
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}	
}
