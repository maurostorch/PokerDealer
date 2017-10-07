package com.example.poker.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
			service.shuffleCards();
		} catch(Exception e) {
			fail("Exception catched:"+e.getMessage());
		}
	}
	
	@Test
	public void testdealOneCard() {
		Card c=null;
		try {
			c = service.getCardFromTop();
			assertNotNull(c);
		} catch (Exception e) {
			fail("Exception catched:"+e.getMessage());
		}
	}
	
	@Test
	public void testdealOneCard_DeckEnd() {
		try {
			for (int i=0;i<52;i++) {
				service.getCardFromTop();
			}
			service.getCardFromTop();
	        fail("Expected an Exception to be thrown");
	    } catch (Exception anIndexOutOfBoundsException) {
	        assertThat(anIndexOutOfBoundsException.getMessage(), is("No more cards"));
	    }
	}
	
}
