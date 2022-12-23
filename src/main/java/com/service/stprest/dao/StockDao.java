package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.stprest.entities.Stocks;

public interface StockDao extends JpaRepository<Stocks,String> {

}
