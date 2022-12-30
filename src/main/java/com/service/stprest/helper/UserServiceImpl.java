package com.service.stprest.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.stprest.dao.TransactionDao;
//import com.service.stprest.dao.TransactionDao;
import com.service.stprest.dao.UserDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.Transactions;
//import com.service.stprest.entities.Transactions;
import com.service.stprest.entities.User;
import com.service.stprest.entities.UserStocks;
import com.service.stprest.entities.Wallet;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private WalletDao walletDao;
	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public List<User> getUsers() {
		List<User> lists = new ArrayList<>();
		try {
			lists = userDao.findAll();
		} catch (Exception e) {
			System.out.println("print");
		}
		return lists;
	}

	@Override
	public User addUser(User user) {
		Wallet wallet = new Wallet();		
		wallet.setBuyingPower(0);
		wallet.setCashAvailable(0);
		wallet.setUser(user);
		walletDao.save(wallet);
		user.setWallet(wallet);
		if(user.getFullName().equals("admin")){
			user.setAdmin(true);
		}
		userDao.save(user);
		return null;
	}

	@Override
	public User getUser(String emailId) {
		Optional<User> user = userDao.findById(emailId);
		if(user.isPresent()) {
			return user.get();
		}
	 throw new RuntimeException("User not found");
	}

	@Override
	public Wallet getWallet(String emailId) {
		return walletDao.findById(emailId).get();
	}
	
	@Override
	public Wallet updateWallet(String emailId, double amount) {
		Wallet wallet = walletDao.findById(emailId).get();
		wallet.setBuyingPower(amount);
		wallet.setCashAvailable(amount);
		walletDao.save(wallet);
		return wallet;
		
	}
	
	public Set<UserStocks> getUserStocks(String emailId){
		Set<UserStocks> lists = userDao.findById(emailId).get().getStocks();
		return lists;
	}

	@Override
	public List<Transactions> getTransaction(String emailId) {
		List<Transactions> lists = new ArrayList<>();
		try {
			lists = transactionDao.findAllByEmailId(emailId);
		} catch (Exception e) {
			System.out.println("print");
		}
		return lists;
	}

	@Override
	public Transactions updateTransaction(String emailId, Transactions transaction) {
		Wallet wallet = walletDao.findById(emailId).get();
		double userCash = wallet.getCashAvailable();
		double transactionAmount = transaction.getTransactionAmount();
		double userBuyingPower = wallet.getBuyingPower();
		if(transaction.getTransactionType().equals("DEPOSIT"))
		{
			userCash+=transactionAmount;
			userBuyingPower+=transactionAmount;
		}
		else {
			userCash-=transactionAmount;
			userBuyingPower-=transactionAmount;	
		}
		wallet.setCashAvailable(userCash);
		wallet.setBuyingPower(userBuyingPower);
		transactionDao.save(transaction);
		return transaction;
	}
}
