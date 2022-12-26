package com.service.stprest.helper;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.stprest.dao.StockDao;
import com.service.stprest.entities.Stock;


@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockDao stockDao;
	
	@Override
	public List<Stock> getStocks() {
		return stockDao.findAll();
	}

	@Override
	public Stock addStock(Stock stock) {
		stockDao.save(stock);
		return null;
	}

}
