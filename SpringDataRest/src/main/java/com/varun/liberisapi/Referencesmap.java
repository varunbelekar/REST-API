package com.varun.liberisapi;

public class Referencesmap {
	private String ref1;
	private String ref2;
	
	public Referencesmap() {
		
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public Referencesmap(String ref1, String ref2) {
		super();
		this.ref1 = ref1;
		this.ref2 = ref2;
	}
	@Override
	public String toString() {
		return "Referencesmap [ref1=" + ref1 + ", ref2=" + ref2 + "]";
	}
	
	
}
