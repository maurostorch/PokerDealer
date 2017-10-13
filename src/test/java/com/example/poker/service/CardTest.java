package com.example.poker.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testCardConstructorForSuit() {
		Card card = new Card("hearts","ace");
		
		assertEquals("hearts", card.getSuit());
		assertNotEquals("ace", card.getSuit());
		
	}
	
	@Test
	public void testCardConstructorForValue() {
		Card card = new Card("hearts","ace");
		
		assertEquals("ace", card.getValue());
		assertNotEquals("hearts", card.getValue());
		
	}
	
	@Test
	public void testEquals() {
		Card c1 = new Card("hearts","ace");
		Card c2 = new Card("hearts","2");
		Card c3 = new Card("clubs","ace");
		Card c4 = new Card("clubs","2");
		Card c5 = new Card("hearts","ace");
		
		assertFalse(c1.equals(null));
		assertTrue(c1.equals(c1));
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertTrue(c1.equals(c5));
		assertFalse(c1.equals(new Integer("1")));
		
	}
	
	@Test
	public void testToString() {
		Card c1 = new Card("hearts","ace");
		
		assertEquals("{\"suit\":\"hearts\",\"value\":\"ace\"}", c1.toString());
	}
	
}
