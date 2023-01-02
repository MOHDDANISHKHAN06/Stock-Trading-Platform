package com.service.stprest.entities;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MarketSchedule {
	
	@Id
	private int id = 1;
	private LocalTime startTime = LocalTime.of(8, 00, 00);
	private LocalTime endTime = LocalTime.of(17, 00, 00);
	private String holidays = "";
		
	
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public int getId() {
		return id;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
