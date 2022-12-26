package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.stprest.entities.User;

public interface UserDao extends JpaRepository<User,String>{

}
