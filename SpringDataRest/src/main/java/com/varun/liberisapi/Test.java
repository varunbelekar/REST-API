package com.varun.liberisapi;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) throws URISyntaxException {
		String advertUrl = "http://marketplace-dev.liberis.co.uk/api/advert?firstName=varun&reference=ID12345&pxHeight=300&pxWidth=240";
		String tokenUrl = "http://marketplace-dev.liberis.co.uk/api/token";
		
		RestTemplate http = new RestTemplate();

		Token token = getToken(tokenUrl, http);
		System.out.println(token);
		
		RequestEntity<?> rq = RequestEntity.get(new URI(advertUrl))
				.accept(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + token.getAccessToken())
				.build();
		
		ResponseEntity<Advert> rs = http.exchange(rq, Advert.class);
		
		Advert advert = rs.getBody();
		System.out.println(advert);
	}

	private static Token getToken(String tokenUrl, RestTemplate http) throws URISyntaxException {
		URI uri = new URI(tokenUrl);
		RequestEntity<Auth> rq = RequestEntity.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(new Auth("testpartner", "secret"));
		
		ResponseEntity<Token> rs = http.exchange(rq, Token.class);
		
		Token token = rs.getBody();
		return token;
	}
}
