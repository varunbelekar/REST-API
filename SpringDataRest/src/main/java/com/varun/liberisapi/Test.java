package com.varun.liberisapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws URISyntaxException {
		String advertUrl = "http://marketplace-dev.liberis.co.uk/api/advert";
		String tokenUrl = "http://marketplace-dev.liberis.co.uk/api/token";
		String acceptUrl = "http://marketplace-dev.liberis.co.uk/api/advert/accept/{reference}";
		
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
		 
		 Accept accept = accept(acceptUrl, http, token);
		 System.out.println(accept.toString());
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
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(advertUrl)
														.queryParam("name", "Varun")
														.queryParam("reference", "ID12345");
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("authorization", "Bearer " + token.getAccessToken());
		
		HttpEntity<Void> httpEntity = new HttpEntity<Void>(httpHeaders);
		ResponseEntity<Advert> responseEntity = http.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Advert.class);
		
		return responseEntity;
	}
	
public static Accept accept(String acceptUrl, RestTemplate http, Token token){
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.add("authorization", "Bearer " + token.getAccessToken());
		
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("reference", "ID12345");
		
		HttpEntity<Accept> entity = new HttpEntity<Accept>(httpHeaders);
		
		URI uri = UriComponentsBuilder.fromUriString(acceptUrl)
														   .buildAndExpand(map)
														   .toUri();
		return http.exchange(uri, HttpMethod.GET, entity, Accept.class).getBody();
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
