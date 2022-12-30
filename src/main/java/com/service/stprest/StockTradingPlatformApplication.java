package com.service.stprest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class StockTradingPlatformApplication {

	public static void main(String[] args) {

		SpringApplication.run(StockTradingPlatformApplication.class, args);		 

	}
	
}
