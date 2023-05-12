package com.moviefandom.dto;

public class Cast {
	private String cast_name;
	private String cast_url;
	private String about_url;
	private String rolePlayed;
	
	public Cast(String cast_name, String cast_url, String about_url){
		this.cast_name = cast_name;
		this.cast_url = cast_url;
		this.about_url = about_url;
	}

	public String getCastName() {
		return cast_name;
	}

	public String getCastUrl() {
		return cast_url;
	}

	public String getAboutUrl() {
		return about_url;
	}
}
