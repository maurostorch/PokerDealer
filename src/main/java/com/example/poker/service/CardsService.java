package com.example.poker.service;

public interface CardsService {
		
	Card getCardFromTop(int tableId) throws Exception;
	
	void shuffleCards(int tableId);

}
