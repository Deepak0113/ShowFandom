package com.moviefandom.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Review {
	private final int id;
	private final String name;
	private final String review;
	private final int rating;
	private final LocalDateTime timestamp;
	
	public Review(int id, String name, String review, int rating,  String timestamp) {
		this.id = id;
		this.name = name;
		this.review = review;
		this.rating = rating;
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.timestamp = LocalDateTime.parse(timestamp, formatter);
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getReview() {
		return review;
	}
	
	public String getTimestamp() {
		return timestamp.toString();
	}

	public int getRating() {
		return rating;
	}
}
