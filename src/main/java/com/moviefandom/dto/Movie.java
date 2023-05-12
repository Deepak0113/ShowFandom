package com.moviefandom.dto;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	private int id;
	private String name;
	private float imdbRating;
	private int rottenOrangeRatting;
	private String posterUrl;
	
	public Movie(int id, String name, float imdbRating, int rottenOrangeRatting, String posterUrl) {
		this.id = id;
		this.name = name;
		this.imdbRating = imdbRating;
		this.rottenOrangeRatting = rottenOrangeRatting;
		this.posterUrl = posterUrl;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getImdbRating() {
		return imdbRating;
	}

	public int getRottenOrangeRatting() {
		return rottenOrangeRatting;
	}

	public String getPosterUrl() {
		return posterUrl;
	}
}
