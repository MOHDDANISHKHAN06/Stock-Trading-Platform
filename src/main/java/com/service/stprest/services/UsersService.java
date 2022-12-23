package com.service.stprest.services;

import java.util.List;
import com.service.stprest.entities.Users;


public interface UsersService {
	
	public List<Users> getUsers();

	public Users addUser(Users user);

}
