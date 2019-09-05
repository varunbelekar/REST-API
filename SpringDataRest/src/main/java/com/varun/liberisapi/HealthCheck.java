package com.varun.liberisapi;

public class HealthCheck {
	private String health;

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public HealthCheck(String health) {
		super();
		this.health = health;
	}

	@Override
	public String toString() {
		return "HealthCheck [health=" + health + "]";
	}
	
	
}
