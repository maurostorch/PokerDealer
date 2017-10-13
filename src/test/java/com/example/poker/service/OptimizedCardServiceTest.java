package com.example.poker.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.poker.OptimizedApplicationTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=OptimizedApplicationTestConfiguration.class)
public class OptimizedCardServiceTest {

	@Autowired
	private CardsService service;
	
	@Test
	public void testShuffle() {
		try {
			service.shuffleCards(1);
		} catch(Exception e) {
			fail("Exception catched:"+e.getMessage());
		}
	}
	
	@Test
	public void testdealOneCard() {
		Card c=null;
		try {
			c = service.getCardFromTop(1);
			assertNotNull(c);
		} catch (Exception e) {
			fail("Exception catched:"+e.getMessage());
		}
	}
	
	@Test
	public void testdealOneCard_DeckEnd() {
		try {
			for (int i=0;i<52;i++) {
				service.getCardFromTop(1);
			}
			service.getCardFromTop(1);
	        fail("Expected an Exception to be thrown");
	    } catch (Exception anIndexOutOfBoundsException) {
	        assertThat(anIndexOutOfBoundsException.getMessage(), is("No more cards"));
	    }
	}
	
	@Test
	public void testmultipleTables() {
		Set<Card> table1 = new HashSet<>();
		Set<Card> table2 = new HashSet<>();
		
		service.shuffleCards(1);
		service.shuffleCards(2);
		
		try {
			table1.add(service.getCardFromTop(1));
			table2.add(service.getCardFromTop(2));
		} catch (Exception e) {
			fail("Not getting cards from deck for tables 1 2");
		}
		
		try {
			table2.add(service.getCardFromTop(2));
			table1.add(service.getCardFromTop(1));
		} catch (Exception e) {
			fail("Not getting cards from deck for tables 2 1");
		}
		
		try {
			for (int i = 0;i<50;i++) {
				table1.add(service.getCardFromTop(1));
				table2.add(service.getCardFromTop(2));
			}
		} catch (Exception e) {
			fail("There is no enough cards in the decks 1 and 2");
		}
		
		assertEquals(52, table1.size());
		assertEquals(52, table2.size());
		
	}
	
}
