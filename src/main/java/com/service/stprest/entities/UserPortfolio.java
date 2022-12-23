package com.service.stprest.entities;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;



@Entity
public class UserPortfolio {
	
	@Id
	private int portfolioID;
	private long numOfShares;
	private double amtInvested;
	private Stocks stock;
	public int getPortfolioID() {
		return portfolioID;
	}
	public void setPortfolioID(int portfolioID) {
		this.portfolioID = portfolioID;
	}
	public long getNumOfShares() {
		return numOfShares;
	}
	public void setNumOfShares(long numOfShares) {
		this.numOfShares = numOfShares;
	}
	public double getAmtInvested() {
		return amtInvested;
	}
	public void setAmtInvested(double amtInvested) {
		this.amtInvested = amtInvested;
	}
	public Stocks getStock() {
		return stock;
	}
	public void setStock(Stocks stock) {
		this.stock = stock;
	}
	public UserPortfolio(int portfolioID, long numOfShares, double amtInvested, Stocks stock) {
		super();
		this.portfolioID = portfolioID;
		this.numOfShares = numOfShares;
		this.amtInvested = amtInvested;
		this.stock = stock;
	}
	
	//update cash
	

}
