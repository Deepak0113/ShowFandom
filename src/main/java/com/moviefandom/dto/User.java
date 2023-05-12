package com.moviefandom.dto;

public class User {
	private String username;
	private String name;
	private String emailid;
	private boolean isBlocked;
	
	public User(String username, String name, String emailid, boolean isBlocked) {
		this.username = username;
		this.name = name;
		this.emailid = emailid;
		this.isBlocked = isBlocked;
	}

	public String getUsername() {
		return username;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public String getName() {
		return name;
	}
	
	public String getEmailid() {
		return emailid;
	}
}
