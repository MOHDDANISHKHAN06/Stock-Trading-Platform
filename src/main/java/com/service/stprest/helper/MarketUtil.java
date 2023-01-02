package com.service.stprest.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.service.stprest.entities.Order;

import lombok.NonNull;

public final class MarketUtil {
	
	private MarketUtil() {}
		
	public static boolean isValidMarketHour(@NonNull String holidaysString, 
			LocalTime startTime,
			LocalTime endTime, 
			@NonNull LocalDate orderDate,
			@NonNull LocalTime orderTime) {
		
		
		if(!holidaysString.isEmpty())
		{
		String[] holidays = holidaysString.split(",");
		
		// check for holidays
		for(String hol : holidays) {
			LocalDate holidayDate = LocalDate.parse(hol);
			if(holidayDate.isEqual(orderDate)) {
				return false;
			}
		}
		}
		
		// check for weekdays
		DayOfWeek dayOfWeek  = orderDate.getDayOfWeek();
		
		if(dayOfWeek.equals(DayOfWeek.SUNDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)) {
			return false;
		}
		
		// check time of order is within market hours
		if(startTime !=null && endTime !=null && (orderTime.isBefore(startTime) || orderTime.isAfter(endTime))) {
			return false;
		}					
		return true;
	}
	
	public static boolean isExpired(Order order) {	
		return order.getExpiry().isBefore(LocalDate.now())? true: false;
		
	}

}
