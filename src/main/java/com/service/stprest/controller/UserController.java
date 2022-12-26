package com.service.stprest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.stprest.entities.Order;
import com.service.stprest.entities.Transactions;
import com.service.stprest.entities.User;
import com.service.stprest.entities.Wallet;
import com.service.stprest.helper.OrderService;
import com.service.stprest.helper.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;

	@GetMapping("/users")
	public List<User> getUsers(){
		return this.userService.getUsers();
	}

	@GetMapping("/users/{emailId}")
	public User getUser(@PathVariable String emailId){
		return this.userService.getUser(emailId);
	}

	@PostMapping("/users")
	public User adduser(@RequestBody User user) {
		return this.userService.addUser(user);
	}

	@PostMapping("/users/{emailId}/order")
	public void placeOrder(@PathVariable String emailId, @RequestBody Order order) {
		this.orderService.addOrder(order);
	}

	//	@GetMapping("/users/{emailId}/order")
	//	public List<Order> getOrder(@PathVariable String emailId) {
	//		this.orderService.getOrder(order);
	//	}


	@GetMapping("/users/{emailId}/wallet")
	public Wallet getWallet(@PathVariable String emailId){
		return this.userService.getWallet(emailId);
	}

	@PostMapping("/users/{emailId}/wallet/{amount}")
	public Wallet updateWallet(@PathVariable String emailId,@PathVariable double amount){
		return this.userService.updateWallet(emailId, amount);
	}

	@GetMapping("/users/{emailId}/transaction")
	public List<Transactions> getTransactions(@PathVariable String emailId){
		return this.userService.getTransaction(emailId);
	}

	@PostMapping("/users/{emailId}/transaction")
	public Transactions updateTransaction(@PathVariable String emailId, @RequestBody Transactions transactions ){
		return this.userService.updateTransaction(emailId, transactions);
	}


}
