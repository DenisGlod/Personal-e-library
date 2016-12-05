package com.example.pel.tests;

public enum PropertiesDB {
	URL("jdbc:mysql://localhost:3306/"), 
	USER("root"), 
	PASSWORD("root");

	private String str;

	private PropertiesDB(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}
}
