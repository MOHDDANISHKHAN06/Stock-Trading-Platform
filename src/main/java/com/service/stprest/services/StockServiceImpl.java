package com.service.stprest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.stprest.dao.StockDao;
import com.service.stprest.entities.Stocks;


@Service
public class StockServiceImpl implements StocksService {
	
	@Autowired
	private StockDao stockDao;
	
	@Override
	public List<Stocks> getStocks() {
		return stockDao.findAll();
	}

	@Override
	public Stocks addStock(Stocks stock) {
		stockDao.save(stock);
		return null;
	}

}
