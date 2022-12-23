package com.service.stprest.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class Wallet {
	
	@Id
	private int ID; //do we need it????
	private double cashAvailable;
	private List<Transactions> historyOfTransactions;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getCashAvailable() {
		return cashAvailable;
	}
	public void setCashAvailable(double cashAvailable) {
		this.cashAvailable = cashAvailable;
	}
	public List<Transactions> getHistoryOfTransactions() {
		return historyOfTransactions;
	}
	public void setHistoryOfTransactions(List<Transactions> historyOfTransactions) {
		this.historyOfTransactions = historyOfTransactions;
	}
	public Wallet(int iD, double cashAvailable, List<Transactions> historyOfTransactions) {
		super();
		ID = iD;
		this.cashAvailable = cashAvailable;
		this.historyOfTransactions = historyOfTransactions;
	}


}
