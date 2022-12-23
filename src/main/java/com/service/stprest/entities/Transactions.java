package com.service.stprest.entities;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Transactions {

	private String transacnType;
	private Date date;
	private Time time;
	private double transacnAmt;
	private String username;   ///doubt?????????
	public Transactions(String transacnType, Date date, Time time, double transacnAmt, String username) {
		super();
		this.transacnType = transacnType;
		this.date = date;
		this.time = time;
		this.transacnAmt = transacnAmt;
		this.username = username;
	}
	public String getTransacnType() {
		return transacnType;
	}
	public void setTransacnType(String transacnType) {
		this.transacnType = transacnType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public double getTransacnAmt() {
		return transacnAmt;
	}
	public void setTransacnAmt(double transacnAmt) {
		this.transacnAmt = transacnAmt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
