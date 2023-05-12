package com.moviefandom.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompleteMovie extends Movie {
	private String description;
	private int duration;
	private String trailerUrl;
	private LocalDate releaseDate;
	private List<Review> reviews;
	private List<String> tags;
	private List<Cast> casts;
	
	public CompleteMovie(int id, String name, float imdbRating, int rottenOrangeRatting, String posterUrl,
			String description, int duration, String trailerUrl, String releaseDate, List<String> tags,
			List<Cast> casts, List<Review> reviews) {
		super(id, name, imdbRating, rottenOrangeRatting, posterUrl);
		this.description = description;
		this.duration = duration;
		this.trailerUrl = trailerUrl;
		this.releaseDate = LocalDate.parse(releaseDate);
		this.tags = new ArrayList<>(tags);
		this.casts = new ArrayList<>(casts);
		this.reviews = new ArrayList<>(reviews);
	}

	public String getDescription() {
		return description;
	}

	public int getDuration() {
		return duration;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public String getReleaseDate() {
		return releaseDate.toString();
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public List<String> getTags() {
		return tags;
	}

	public List<Cast> getCasts() {
		return casts;
	}
}
