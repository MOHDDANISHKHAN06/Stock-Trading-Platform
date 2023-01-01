package com.service.stprest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.service.stprest.dao.StockDao;
import com.service.stprest.entities.Stock;

@Component
@Scope("prototype")
@EnableTransactionManagement
public class RandomStockPriceGenerartorService implements Runnable {
	
	@Autowired
	private StockDao stockdao;
	
	@Override
	public void run() {

		while(true){
			List<Stock> stockList = stockdao.findAll();
			for(Stock stock : stockList) {
				double random = (Math.random() * 49) + 1;
				System.out.println(random);
				if(stock !=null)
				{
					double newPrice = ThreadLocalRandom.current().nextDouble(stock.getCurrentPrice() -10 , stock.getCurrentPrice() + 10);
//					double currPrice = stock.getCurrentPrice();
//					double newPrice = ((currPrice % 2) == 0) ? currPrice + random : currPrice - random;
					if(newPrice <= 0 ) {
						newPrice = 100;
					}
					if(newPrice > stock.getDayHigh()) stock.setDayHigh(newPrice);
					if (newPrice < stock.getDayLow()) stock.setDayLow(newPrice);
					stock.setCurrentPrice(newPrice);
					stockdao.save(stock);
				}
			}
			try {
				// sleep for 5 sec
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
