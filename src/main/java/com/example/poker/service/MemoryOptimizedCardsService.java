package com.example.poker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Logger;

public class MemoryOptimizedCardsService implements CardsService {
	
	public final static Logger log = Logger.getLogger(MemoryOptimizedCardsService.class.getName());

	private final static String suits[] = {"hearts", "spades", "clubs", "diamonds"};
	private final static String values[] = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	private List<Card> deck;
	private List<Card> out;
	
	public MemoryOptimizedCardsService() {
		this.deck = new LinkedList<>();
		for (String s:Arrays.asList(suits)) {
			for (String v:Arrays.asList(values)) {
				this.deck.add(new Card(s,v));
			}
		}
		this.out = new ArrayList<>();
	}
	
	/**
	 * The optimization is related to avoiding handling the entire list
	 * during the shuffle and enjoy the O(1) to remove from LinkedList
	 * when using an Iterator and its remove method
	 */
	@Override
	public Card getCardFromTop() throws Exception {
		log.fine("Dealing one card.");
		try {
			int next = new Random(System.currentTimeMillis()).nextInt(this.deck.size()==0?1:this.deck.size());
			Iterator<Card> iter = this.deck.listIterator(next);
			Card c = iter.next();
			iter.remove();
			out.add(c);
			return c;
		} catch (NoSuchElementException e) {
			log.fine("No more cards");
			throw new Exception("No more cards");
		}
	}

	@Override
	public void shuffleCards() {
		log.fine("Shuffling..");
		this.reset();
		log.fine("Shuffling.. ended.");
	}

	private void reset() {
		this.deck.addAll(this.out);
		this.out.clear();
	}

}
