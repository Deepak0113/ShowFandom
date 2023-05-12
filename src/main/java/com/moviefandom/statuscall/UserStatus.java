package com.moviefandom.statuscall;

import com.moviefandom.dto.User;

public class UserStatus extends Status{
	private User user;
	
	public UserStatus(String status) {
		super(status);
	}
	
	public UserStatus(String status, User user) {
		super(status);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
