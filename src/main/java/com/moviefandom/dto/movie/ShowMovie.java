package com.moviefandom.dto.movie;

import java.time.LocalDate;
import java.util.List;

import com.moviefandom.dto.Cast;

public class ShowMovie {
	private String id;
	private String name;
	private String synopsis;
	private int ratingTypeID;
	private int duration;
	private LocalDate releaseDate;
	private String language;
	private float imdbRating;
	private int rottenOrangeRating;
	private float showFandomRating;
	private String posterImgUrl;
	private String trailerUrl;
	private String countryid;
	private String[] genres;
	private List<Cast> casts;
}
