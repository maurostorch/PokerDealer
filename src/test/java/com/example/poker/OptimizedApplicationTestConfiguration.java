package com.example.poker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.poker.service.CardsService;
import com.example.poker.service.MemoryOptimizedCardsService;

@Configuration
public class OptimizedApplicationTestConfiguration {

	@Bean
	public CardsService cardsService() {
		return new MemoryOptimizedCardsService();
	}
	
}
