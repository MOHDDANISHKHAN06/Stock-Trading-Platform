package com.service.stprest.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

public class UserTransactionId implements Serializable{
	
    @Column(name = "transactionId") 
    @SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_JUST_FOR_TEST", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
    private int transactionId; 
      
	@Column(name = "emailId")
	private String emailId;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getUsername() {
		return emailId;
	}

	public void setUsername(String emailId) {
		this.emailId = emailId;
	}  

   
}
