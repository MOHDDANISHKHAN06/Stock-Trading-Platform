package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.stprest.entities.UserStockId;
import com.service.stprest.entities.UserStocks;


public interface UserStockDao extends JpaRepository<UserStocks, UserStockId>{

}
