package com.service.stprest.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.service.stprest.entities.Transactions;


public interface TransactionDao extends JpaRepository<Transactions, Integer> {
	
    List<Transactions> findAllByEmailId(String emailId);

}
