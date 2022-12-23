package com.service.stprest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.stprest.entities.Stocks;
import com.service.stprest.services.StocksService;

@RestController
public class StockController {
	
	@Autowired
	StocksService stockService;
	
	@GetMapping("/stocks")
	public List<Stocks> getStocks(){
		return this.stockService.getStocks();
		
		
	}
	
	@PostMapping("/stocks")
	public Stocks addStock(@RequestBody Stocks stock) {
		return this.stockService.addStock(stock);
		
	}
	
	
}
