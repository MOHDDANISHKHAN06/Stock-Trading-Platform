package com.service.stprest.helper;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.stprest.dao.MarketScheduleDao;
import com.service.stprest.entities.MarketSchedule;


@Service
public class MarketScheduleServiceImpl implements MarketScheduleService{
	
	@Autowired
	private MarketScheduleDao marketScheduleDao;

	@Override
	public void changeMarketHours(LocalTime startTime, LocalTime endTime) {
		MarketSchedule marketSchedule;
		if(!marketScheduleDao.existsById(1)) {
			 marketSchedule = new MarketSchedule();
		}
		else {
			 marketSchedule = marketScheduleDao.findById(1).get();
		}
		marketSchedule.setStartTime(startTime);
		marketSchedule.setEndTime(endTime);
		marketScheduleDao.save(marketSchedule);
	}

	@Override
	public void addHoliday(MarketSchedule mktSchedule) {
		
		if(!mktSchedule.getHolidays().isEmpty()) {
			
			MarketSchedule marketSchedule;
			if(!marketScheduleDao.existsById(1)) {
				 marketSchedule = new MarketSchedule();
			}
			else {
				 marketSchedule = marketScheduleDao.findById(1).get();
			}		
			String existingHolidays =(marketSchedule.getHolidays().isEmpty()) ? mktSchedule.getHolidays() : marketSchedule.getHolidays()+ ',' + mktSchedule.getHolidays();
			marketSchedule.setHolidays(existingHolidays);
			marketScheduleDao.save(marketSchedule);
		}
	}		

	@Override
	public MarketSchedule getMarketSchedule(int Id) {
				return marketScheduleDao.findById(Id).get();
	}

//	@Override
//	public void deleteHoliday(MarketSchedule mktSchedule) {
//		
//		MarketSchedule marketSchedule;
//		marketSchedule = marketScheduleDao.findById(1).get();
//		
//		String[] holidays = marketSchedule.getHolidays().split(",");
//		String newHolidays = "";
//		for(String s : holidays)
//		{
//			if(s.equals(mktSchedule.getHolidays())) {
//				continue;
//			}
//			newHolidays= newHolidays + "," + s;
//		}
//		marketSchedule.setHolidays(newHolidays);
//		marketScheduleDao.save(marketSchedule);
//	}
}
