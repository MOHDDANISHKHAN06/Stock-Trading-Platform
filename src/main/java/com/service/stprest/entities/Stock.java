package com.service.stprest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stock {
	
	@Id
	private String ticker;
	private String companyName;
	private double initialPrice;
	private double currentPrice;
	private long volume;
	private double dayHigh;
	private double dayLow;
	private double marketCapitalisation;
	
	
	public double getMarketCapitalisation() {
		return marketCapitalisation;
	}

	public void setMarketCapitalisation(double marketCapitalisation) {
		this.marketCapitalisation = marketCapitalisation;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public double getDayHigh() {
		return dayHigh;
	}
	public void setDayHigh(double dayHigh) {
		this.dayHigh = dayHigh;
	}
	public double getDayLow() {
		return dayLow;
	}
	public void setDayLow(double dayLow) {
		this.dayLow = dayLow;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	
	
}
