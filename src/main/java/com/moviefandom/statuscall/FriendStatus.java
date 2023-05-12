package com.moviefandom.statuscall;

public class FriendStatus extends Status{
	private String message;
	
	public FriendStatus(String status, String message) {
		super(status);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
