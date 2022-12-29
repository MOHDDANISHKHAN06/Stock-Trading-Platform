package com.service.stprest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.stprest.entities.Stock;
import com.service.stprest.helper.StockService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@GetMapping("/stocks")
	public List<Stock> getStocks(){
		return this.stockService.getStocks();
	}
	
	@PostMapping("/stocks")
	public Stock addStock(@RequestBody Stock stock) {
		return this.stockService.addStock(stock);
		
	}
}
