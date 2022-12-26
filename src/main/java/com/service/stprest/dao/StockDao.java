package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.stprest.entities.Stock;

public interface StockDao extends JpaRepository<Stock,String> {

}
