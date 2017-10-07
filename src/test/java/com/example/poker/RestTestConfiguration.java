package com.example.poker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.poker.rest.Dealer;
import com.example.poker.service.CardsService;
import com.example.poker.service.MemoryCardsService;

@Configuration
public class RestTestConfiguration {

	@Bean
	public CardsService getCardService() {
		return new MemoryCardsService();
	}
	
	@Bean
	public Dealer getDealer() {
		return new Dealer();
	}
	
}
