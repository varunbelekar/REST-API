package com.varun.liberisapi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class BaseApiRequest {
	
	private String requestURI;

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

}
