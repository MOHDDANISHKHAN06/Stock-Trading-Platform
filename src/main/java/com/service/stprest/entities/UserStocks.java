package com.service.stprest.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;


@Entity
public class UserStocks {

	@EmbeddedId
	private UserStockId userStockId;

	@ManyToOne
	@MapsId("emailId")
	@JsonIgnore
	private User user;

	private long numOfShares;

	public UserStockId getId() {
		return userStockId;
	}

	public void setId(UserStockId id) {
		this.userStockId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(long numOfShares) {
		this.numOfShares = numOfShares;
	}


}
