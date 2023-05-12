package com.moviefandom.statuscall;

import com.moviefandom.dto.movie.ShowMovie;

public class ShowStatus extends Status{
	private ShowMovie showMovie;
	
	public ShowStatus(String status) {
		super(status);
	}
	
	public ShowStatus(String status, ShowMovie showMovie) {
		super(status);
		this.showMovie = showMovie;
	}
	
	public ShowMovie getShowMovie() {
		return showMovie;
	}
}
