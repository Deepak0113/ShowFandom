package com.moviefandom.dto;

import java.time.LocalDate;

public class ShowPreview {
	private String id;
	private String name;
	private LocalDate releaseDate;
	private float imdbRating;
	private int rottenTomatoRating;
	private int showFandomRating;
	private String posterImgUrl;
	private String ratingType;
	
	public ShowPreview(String id, String name, String releaseDate, float imdbRating,
			int rottenToamtoRating, int showFandomRating, String posterImgUrl,
			String ratingType) {
		this.id = id;
		this.name = name;
		this.releaseDate = LocalDate.parse(releaseDate);
		this.imdbRating = imdbRating;
		this.rottenTomatoRating = rottenToamtoRating;
		this.showFandomRating = showFandomRating;
		this.posterImgUrl = posterImgUrl;
		this.ratingType = ratingType;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public float getImdbRating() {
		return imdbRating;
	}

	public int getRottenTomatoRating() {
		return rottenTomatoRating;
	}

	public int getShowFandomRating() {
		return showFandomRating;
	}

	public String getPosterImgUrl() {
		return posterImgUrl;
	}
	
	public String getRatingType() {
		return ratingType;
	}
}
