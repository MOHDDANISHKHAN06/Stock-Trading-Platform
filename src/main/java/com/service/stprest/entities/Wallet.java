package com.service.stprest.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Wallet {

	@Id
	private String id;
	private double cashAvailable;
	private double buyingPower;

	@OneToOne
	@MapsId
	@JoinColumn(name = "emailId")
	@JsonIgnore
	private User user;	

	public double getBuyingPower() {
		return buyingPower;
	}

	public void setBuyingPower(double buyingPower) {
		this.buyingPower = buyingPower;
	}

	public double getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(double cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
