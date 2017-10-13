package com.example.poker.rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Path("/table/{tableid}/deck")
public class Dealer {

	@Autowired
	CardsService cardService;
	
	@POST
	@PermitAll
	public void shuffle(@PathParam("tableid") int tableId) {
		cardService.shuffleCards(tableId);
	}
	
	@GET
	@PermitAll
	@Produces("application/json")
	public Card dealOneCard(@PathParam("tableid") int tableId) {
		try {
			return cardService.getCardFromTop(tableId);
		} catch (Exception e) {
			throw new WebApplicationException("No more cards", 404);
		}
	}
	
}
