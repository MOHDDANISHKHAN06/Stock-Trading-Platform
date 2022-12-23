package com.service.stprest.services;

import java.util.List;
import com.service.stprest.entities.Stocks;

public interface StocksService {
	
	public List<Stocks> getStocks();

	public Stocks addStock(Stocks stock);

}
