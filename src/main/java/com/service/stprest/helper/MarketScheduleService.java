package com.service.stprest.helper;

import java.time.LocalTime;

import com.service.stprest.entities.MarketSchedule;

public interface MarketScheduleService {
	
	public void changeMarketHours(LocalTime startTime, LocalTime endTime);
	
	public void addHoliday(MarketSchedule mktSchedule);
	
	public MarketSchedule getMarketSchedule(int Id);
}
