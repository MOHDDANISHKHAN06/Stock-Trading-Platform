package com.service.stprest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.stprest.entities.MarketSchedule;

public interface MarketScheduleDao extends JpaRepository<MarketSchedule,Integer> {

}
