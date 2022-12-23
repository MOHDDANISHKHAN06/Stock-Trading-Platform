package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.stprest.entities.Users;

public interface UsersDao extends JpaRepository<Users,String>{

}
