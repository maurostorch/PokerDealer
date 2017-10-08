package com.example.poker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class MemoryCardsService implements CardsService {
	
	public final static Logger log = Logger.getLogger(MemoryCardsService.class.getName());

	private final static String suits[] = {"hearts", "spades", "clubs", "diamonds"};
	private final static String values[] = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	private Map<Integer,List<Card>> decks;
	private Map<Integer,List<Card>> outs;
	
	public MemoryCardsService() {
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
		this.outs.put(tableId, new ArrayList<>());
	}
	
	private List<Card> getDeck(int tableId) {
		if (!this.decks.containsKey(tableId)) {
			init(tableId);
		}
		return this.decks.get(tableId);
	}
	
	private List<Card> getOut(int tableId) {
		if (!this.outs.containsKey(tableId)) {
			init(tableId);
		}
		return this.outs.get(tableId);
	}
	
	@Override
	public Card getCardFromTop(int tableId) throws Exception {
		List<Card> deck = this.getDeck(tableId);
		List<Card> out = this.getOut(tableId);
		log.fine("Dealing one card.");
		try {
			Card c = deck.remove(deck.size()-1);
			out.add(c);
			return c;
		} catch (IndexOutOfBoundsException e) {
			log.fine("No more cards");
			throw new Exception("No more cards");
		}
	}

	@Override
	public void shuffleCards(int tableId) {
		List<Card> deck = this.getDeck(tableId);
		log.fine("Shuffling..");
		Random r = new Random(System.currentTimeMillis());
		this.reset(tableId);
		int size = deck.size();
		for (int i = size-1;i>0;i--) {
			int random = r.nextInt(i+1);
			Card _t = deck.get(i);
			deck.set(i, deck.get(random));
			deck.set(random, _t);
		}
		log.fine("Shuffling.. ended.");
	}

	private void reset(int tableId) {
		this.decks.get(tableId).addAll(this.outs.get(tableId));
		this.outs.get(tableId).clear();
	}

}
