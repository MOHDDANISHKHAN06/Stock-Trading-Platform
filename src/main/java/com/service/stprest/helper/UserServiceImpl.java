package com.service.stprest.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//	private TransactionDao transactionDao;
	
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
		//System.out.println(walletDao.findById(emailId).get());
		//System.out.println("kjbckjjcjkdcc");
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

	@Override
	public List<Transactions> getTransaction(String emailId) {
		List<Transactions> lists = new ArrayList<>();
		try {
//			lists = transactionDao.findAll();
		} catch (Exception e) {
			System.out.println("print");
		}
		return lists;
	}

	@Override
	public Transactions updateTransaction(String emailId, Transactions transaction) {
		
		//deposit/withdraw
		//updatewallet
		//update transaction
		Wallet wallet = walletDao.findById(emailId).get();
		double userCash = wallet.getCashAvailable();
		double transactionAmount = transaction.getTransacnAmt();
		double userBuyingPower = wallet.getBuyingPower();
		
		double newCashAmount = (transaction.getTransacnType().equals("DEPOSIT"))? userCash + transactionAmount : userCash - transactionAmount;
		
		
		wallet.setCashAvailable(newCashAmount);
		
		
		return null;
	}


	
}
