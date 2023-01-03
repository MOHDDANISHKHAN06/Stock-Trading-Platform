package com.service.stprest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.service.stprest.dao.MarketScheduleDao;
import com.service.stprest.dao.StockDao;
import com.service.stprest.entities.MarketSchedule;
import com.service.stprest.entities.Stock;
import com.service.stprest.helper.MarketUtil;

@Component
@Scope("prototype")
@EnableTransactionManagement
public class RandomStockPriceGenerartorService implements Runnable {
	
	@Autowired
	private StockDao stockdao;
	
	@Autowired
	private MarketScheduleDao marketScheduleDao;
	
	@Override
	public void run() {

		while(true){
			MarketSchedule marketSchedule;
			
			if(!marketScheduleDao.existsById(1)) {
				marketSchedule = new MarketSchedule();
				marketScheduleDao.save(marketSchedule);
			}
			marketSchedule = marketScheduleDao.findById(1).get();
			
			// Check if order is in market hours before executing it
			if(!MarketUtil.isValidMarketHour(marketSchedule.getHolidays(), marketSchedule.getStartTime(), marketSchedule.getEndTime(), LocalDate.now(), LocalTime.now())) {
				try {
					Thread.sleep(15 * 60 * 1000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
				
			List<Stock> stockList = stockdao.findAll();
			for(Stock stock : stockList) {
				
				LocalTime currentTime = LocalTime.now();
				marketSchedule = marketScheduleDao.findById(1).get();
				
				//Updating opening price when market opens
				if(currentTime.equals(marketSchedule.getStartTime())) {
					stock.setInitialPrice(stock.getCurrentPrice());
				}

				double random = (Math.random() * 49) + 1;
				System.out.println(random);
				if(stock !=null)
				{
					double newPrice = ThreadLocalRandom.current().nextDouble(stock.getCurrentPrice() -10 , stock.getCurrentPrice() + 10);
					if(newPrice <= 0 ) {
						newPrice = 100;
					}
					if(newPrice > stock.getDayHigh()) stock.setDayHigh(newPrice);
					if (newPrice < stock.getDayLow()) stock.setDayLow(newPrice);
					stock.setCurrentPrice(newPrice);
					stock.setMarketCapitalisation(newPrice*stock.getVolume());
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
