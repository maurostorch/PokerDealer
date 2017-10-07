package com.example.poker;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.poker.rest.Dealer;

@Component
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		registerEndpoints();
	}
	
	private void registerEndpoints() {
		register(Dealer.class);
	}

}
