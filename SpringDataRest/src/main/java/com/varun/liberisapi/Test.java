package com.varun.liberisapi;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

		
		 ResponseEntity<Advert> rs = getAdvert(advertUrl, token, http);
		 System.out.println(rs.getBody());
		 /*
		 * HealthCheck helCheck = healthCheck(http);
		 * System.out.println(helCheck.toString());
		 * 
		 * Referencesmap refMap = getReferenceMap(http, token);
		 * System.out.println(refMap.toString());
		 */
	}

	public static ResponseEntity<Advert> getAdvert(String advertUrl, Token token, RestTemplate http)
			throws URISyntaxException {
		/*
		 * Using RequestEntity
		 * RequestEntity<?> rq = RequestEntity.get(new
		 * URI(advertUrl)).accept(MediaType.APPLICATION_JSON) .header("authorization",
		 * "Bearer " + token.getAccessToken()).build();
		 * 
		 * return http.exchange(rq, Advert.class);
		 */
		URI uri = new URI(advertUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("authorization", "Bearer " + token.getAccessToken());
		
		HttpEntity<Void> httpEntity = new HttpEntity<Void>(httpHeaders);
		ResponseEntity<Advert> responseEntity = http.exchange(uri, HttpMethod.GET, httpEntity, Advert.class);
		return responseEntity;
	}

	public static HealthCheck healthCheck(RestTemplate http) {
		HealthCheck helCheck = http.getForObject("http://marketplace-dev.liberis.co.uk/api/healthcheck",
				HealthCheck.class);
		return helCheck;
	}

	public static Referencesmap getReferenceMap(RestTemplate http, Token token) throws URISyntaxException {
		String referenceMapUrl = "http://marketplace-dev.liberis.co.uk/api/referencesmap";
		URI uri = new URI(referenceMapUrl);
		RequestEntity<?> requestEntity = RequestEntity.get(uri)
													 .accept(MediaType.APPLICATION_JSON)
													 .header("authorization", "Bearer " + token.getAccessToken())
													 .build();
		return http.exchange(requestEntity, Referencesmap.class).getBody();
	}

	private static Token getToken(String tokenUrl, RestTemplate http) throws URISyntaxException {
		URI uri = new URI(tokenUrl);
		HttpEntity<Auth> httpEntity = new HttpEntity<Auth>(new Auth("testpartner", "secret"));
		ResponseEntity<Token> responseEntity = http.postForEntity(uri, httpEntity, Token.class);
		return responseEntity.getBody();
	}
}
