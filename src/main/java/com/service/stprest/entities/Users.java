package com.service.stprest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	private String userName;
	private String email;
	private String password;
	private String name;
	boolean adminCheck;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdminCheck() {
		return adminCheck;
	}
	public void setAdminCheck(boolean adminCheck) {
		this.adminCheck = adminCheck;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String userName, String email, String password, String name, boolean adminCheck) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.adminCheck = adminCheck;
	}


	
	

}
