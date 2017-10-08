package com.example.poker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.poker.ApplicationTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfiguration.class)
public class CardServiceTest {

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
	
}
