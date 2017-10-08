package com.example.poker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class MemoryCardsService implements CardsService {
	
	public final static Logger log = Logger.getLogger(MemoryCardsService.class.getName());

	private final static String suits[] = {"hearts", "spades", "clubs", "diamonds"};
	private final static String values[] = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	private List<Card> deck;
	private List<Card> out;
	
	public MemoryCardsService() {
		this.deck = new ArrayList<>();
		for (String s:Arrays.asList(suits)) {
			for (String v:Arrays.asList(values)) {
				this.deck.add(new Card(s,v));
			}
		}
		this.out = new ArrayList<>();
	}
	
	@Override
	public Card getCardFromTop() throws Exception {
		log.fine("Dealing one card.");
		try {
			Card c = this.deck.remove(this.deck.size()-1);
			out.add(c);
			return c;
		} catch (IndexOutOfBoundsException e) {
			log.fine("No more cards");
			throw new Exception("No more cards");
		}
	}

	@Override
	public void shuffleCards() {
		log.fine("Shuffling..");
		Random r = new Random(System.currentTimeMillis());
		this.reset();
		int size = this.deck.size();
		for (int i = size-1;i>0;i--) {
			int random = r.nextInt(i+1);
			Card _t = this.deck.get(i);
			this.deck.set(i, this.deck.get(random));
			this.deck.set(random, _t);
		}
		log.fine("Shuffling.. ended.");
	}

	private void reset() {
		this.deck.addAll(this.out);
		this.out.clear();
	}

}
