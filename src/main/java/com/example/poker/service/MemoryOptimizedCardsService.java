package com.example.poker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Logger;

public class MemoryOptimizedCardsService implements CardsService {
	
	public final static Logger log = Logger.getLogger(MemoryOptimizedCardsService.class.getName());

	private final static String suits[] = {"hearts", "spades", "clubs", "diamonds"};
	private final static String values[] = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	private Map<Integer,List<Card>> decks;
	private Map<Integer,List<Card>> outs;
	
	public MemoryOptimizedCardsService() {
		this.decks = new HashMap<>();
		this.outs  = new HashMap<>();
	}
	
	private void init(int tableId) {
		this.decks.put(tableId,new LinkedList<>());
		for (String s:Arrays.asList(suits)) {
			for (String v:Arrays.asList(values)) {
				this.decks.get(tableId).add(new Card(s,v));
			}
		}
	}
	
	private List<Card> getDeck(int tableId) {
		if (!this.decks.containsKey(tableId)) {
			init(tableId);
		}
		return this.decks.get(tableId);
	}
	
	private List<Card> getOut(int tableId) {
		if (!this.outs.containsKey(tableId)) {
			this.outs.put(tableId, new ArrayList<>());
		}
		return this.outs.get(tableId);
	}
	
	/**
	 * The optimization is related to avoiding handling the entire list
	 * during the shuffle and enjoy the O(1) to remove from LinkedList
	 * when using an Iterator and its remove method
	 */
	@Override
	public Card getCardFromTop(int tableId) throws Exception {
		List<Card> deck = this.getDeck(tableId);
		synchronized (deck) {
			List<Card> out = this.getOut(tableId);
			log.fine("Dealing one card.");
			try {
				int next = new Random(System.currentTimeMillis()).nextInt(deck.size()==0?1:deck.size());
				Iterator<Card> iter = deck.listIterator(next);
				Card c = iter.next();
				iter.remove();
				out.add(c);
				return c;
			} catch (NoSuchElementException e) {
				log.fine("No more cards");
				throw new Exception("No more cards");
			}
		}
	}

	@Override
	public void shuffleCards(int tableId) {
		synchronized (this.getDeck(tableId)) {
			log.fine("Shuffling..");
			this.reset(tableId);
			log.fine("Shuffling.. ended.");
		}
	}

	private void reset(int tableId) {
		this.getDeck(tableId).addAll(this.getOut(tableId));
		this.getOut(tableId).clear();
	}

}
