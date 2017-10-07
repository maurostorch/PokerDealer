package com.example.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.poker.service.CardsService;
import com.example.poker.service.MemoryCardsService;

@SpringBootApplication
public class PokerApplication extends SpringBootServletInitializer{

	@Bean
	public CardsService getCardService() {
		return new MemoryCardsService();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PokerApplication.class, args);
	}
}
