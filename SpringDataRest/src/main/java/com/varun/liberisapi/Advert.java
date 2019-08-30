package com.varun.liberisapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Advert {
	private AdvertData advertData;
	private Link link;
	
	public AdvertData getAdvertData() {
		return advertData;
	}
	public void setAdvertData(AdvertData advertData) {
		this.advertData = advertData;
	}
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "Advert [\n\t" + advertData + ",\n\t" + link + "\n]";
	}

}