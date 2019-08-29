package com.varun.apiintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		logger.info(quote.toString());
	}
}
