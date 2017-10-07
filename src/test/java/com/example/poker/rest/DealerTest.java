package com.example.poker.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.poker.RestTestConfiguration;
import com.example.poker.service.Card;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RestTestConfiguration.class)
public class DealerTest {

	@Autowired
	private Dealer service;
	
	@Test
	public void testShuffle() {
		try {
			service.shuffle();
		} catch(Exception e) {
			fail("Exception catched:"+e.getMessage());
		}
	}
	
	@Test
	public void testdealOneCard() {
		Card c=null;
		try {
			c = service.dealOneCard();
			assertNotNull(c);
		} catch (Exception e) {
			fail("Exception catched:"+e.getMessage());
		}
	}
	
	@Test
	public void testdealOneCard_DeckEnd() {
		try {
			for (int i=0;i<52;i++) {
				service.dealOneCard();
			}
			service.dealOneCard();
	        fail("Expected an Exception to be thrown");
	    } catch (Exception anIndexOutOfBoundsException) {
	        assertThat(anIndexOutOfBoundsException.getMessage(), is("No more cards"));
	    }
	}
	
}
