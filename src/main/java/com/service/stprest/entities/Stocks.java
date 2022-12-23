package com.service.stprest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stocks {
	
	@Id
	private String companyName;
	private String stockTicker;
	private double initialPrice;
	private long volume;
	public Stocks(String companyName, String stockTicker, double initialPrice, long volume) {
		super();
		this.companyName = companyName;
		this.stockTicker = stockTicker;
		this.initialPrice = initialPrice;
		this.volume = volume;
	}
	public Stocks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStockTicker() {
		return stockTicker;
	}
	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	@Override
	public String toString() {
		return "Stocks [companyName=" + companyName + ", stockTicker=" + stockTicker + ", initialPrice=" + initialPrice
				+ ", volume=" + volume + "]";
	}
	

}
