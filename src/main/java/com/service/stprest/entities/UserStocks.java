package com.service.stprest.entities;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;


@Entity
public class UserStocks {

	@EmbeddedId
	private UserStockId userStockId;

	@ManyToOne
	@MapsId("emailId")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("ticker")
	private Stock stock;

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
		//		if(user != null) {
		//			if(this.user!=null) {
		//				this.user.setStocks(null);
		//			} else {
		//				this.user.setStocks(user.getStocks());
		//			}
		//			
		//		}
		this.user = user;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public long getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(long numOfShares) {
		this.numOfShares = numOfShares;
	}


}
