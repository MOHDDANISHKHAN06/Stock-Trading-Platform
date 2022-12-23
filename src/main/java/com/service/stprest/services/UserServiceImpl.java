package com.service.stprest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.stprest.dao.UsersDao;
import com.service.stprest.entities.Users;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	private UsersDao userDao;
	
	
	@Override
	public List<Users> getUsers() {
		return userDao.findAll();
	}

	@Override
	public Users addUser(Users user) {
		userDao.save(user);
		return null;
	}

}
