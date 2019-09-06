package com.varun.liberisapi;

public class Accept {
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Accept(String link) {
		super();
		this.link = link;
	}

	@Override
	public String toString() {
		return "Accept [link=" + link + "]";
	}
	
	
}
