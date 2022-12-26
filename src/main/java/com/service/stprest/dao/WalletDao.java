package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.stprest.entities.Wallet;


public interface WalletDao extends JpaRepository<Wallet, String>{

}
