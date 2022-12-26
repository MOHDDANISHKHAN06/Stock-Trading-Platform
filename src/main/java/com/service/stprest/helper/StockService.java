package com.service.stprest.helper;

import java.util.List;
import com.service.stprest.entities.Stock;

public interface StockService {
	
	public List<Stock> getStocks();

	public Stock addStock(Stock stock);

}
