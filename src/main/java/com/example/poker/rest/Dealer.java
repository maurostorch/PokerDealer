package com.example.poker.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.poker.service.Card;
import com.example.poker.service.CardsService;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "card")
@Path("/deck")
public class Dealer {

	@Autowired
	CardsService cardService;
	
	@POST
	@PermitAll
	public void shuffle() {
		cardService.shuffleCards();
	}
	
	@GET
	@PermitAll
	@Produces("application/json")
	public Card dealOneCard() {
		try {
			return cardService.getCardFromTop();
		} catch (Exception e) {
			throw new WebApplicationException("No more cards", 404);
		}
	}
	
}
