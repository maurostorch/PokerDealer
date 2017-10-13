package com.example.poker.service;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "card")
public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "suit")
	private String suit;
	@XmlAttribute(name = "value")
	private String value;

	public Card(String suit, String value) {
		super();
		this.suit = suit;
		this.value = value;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "{\"suit\":\""+this.suit+"\",\"value\":\""+this.value+"\"}";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Card card = (Card)o;
		return Objects.equals(suit, card.suit) &&
				Objects.equals(value, card.value);
	}

}
