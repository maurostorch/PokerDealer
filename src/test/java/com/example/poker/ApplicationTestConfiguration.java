package com.example.poker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.poker.service.CardsService;
import com.example.poker.service.MemoryCardsService;

@Configuration
public class ApplicationTestConfiguration {

	@Bean
	public CardsService cardsService() {
		return new MemoryCardsService();
	}
	
}
