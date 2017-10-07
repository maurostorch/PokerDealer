package com.example.poker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryCardsService implements CardsService {

	private final static String suits[] = {"hearts", "spades", "clubs", "diamonds"};
	private final static String values[] = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	private List<Card> deck;
	private List<Card> out;
	
	public MemoryCardsService() {
		// This is a LinkedList since adding and removing elements in the middle during shuffle is O(1)
		this.deck = new LinkedList<>();
		for (String s:Arrays.asList(suits)) {
			for (String v:Arrays.asList(values)) {
				this.deck.add(new Card(s,v));
			}
		}
		// This is an ArrayList since is used only to store
		this.out = new ArrayList<>();
	}
	
	@Override
	public Card getCardFromTop() throws Exception {
		try {
			Card c = this.deck.remove(0);
			out.add(c);
			return c;
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("No more cards");
		}
	}

	@Override
	public void shuffleCards() {
		Random r = new Random(System.currentTimeMillis());
		this.reset();
		int size = this.deck.size();
		for (int i = 0;i<size;i++) {
			Card _t = this.deck.remove(r.nextInt(size));
			this.deck.add(r.nextInt(size), _t);
		}
	}

	private void reset() {
		this.out.addAll(deck);
		this.deck.clear();
		this.deck.addAll(this.out);
		this.out.clear();
	}

}
