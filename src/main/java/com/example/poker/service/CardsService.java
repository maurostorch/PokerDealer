package com.example.poker.service;

public interface CardsService {
		
	Card getCardFromTop() throws Exception;
	
	void shuffleCards();

}
