package com.service.stprest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.stprest.entities.Users;
import com.service.stprest.services.UsersService;

@RestController
public class UserController {
	
	@Autowired
	UsersService userService;
	
	@GetMapping("/users")
	public List<Users> getUsers(){
		return this.userService.getUsers();
		
		
	}
	
	@PostMapping("/users")
	public Users adduser(@RequestBody Users user) {
		return this.userService.addUser(user);
		
	}
	

}
