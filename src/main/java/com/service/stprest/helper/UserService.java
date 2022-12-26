package com.service.stprest.helper;

import java.util.List;

import com.service.stprest.entities.Transactions;
import com.service.stprest.entities.User;
import com.service.stprest.entities.Wallet;


public interface UserService {
	
	public User getUser(String emailId);
	
	public List<User> getUsers();

	public User addUser(User user);

	public Wallet getWallet(String emailId);

	public Wallet updateWallet(String emailId, double amount);

	public List<Transactions> getTransaction(String emailId);

	public Transactions updateTransaction(String emailId, Transactions transactions);

}
