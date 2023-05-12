package com.moviefandom.servlets.home.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.moviefandom.dto.ShowPreview;
import com.moviefandom.repository.Repository;

/**
 * Servlet implementation class SearchShowServlet
 */
@WebServlet("/SearchShowServlet")
public class SearchShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String showType = request.getParameter("showtype");
		String name = request.getParameter("searchname");
		String ratingType = request.getParameter("ratingtype");
		String country = request.getParameter("country");
		String[] genres = request.getParameter("genres").split(",");
		JSONArray arr = new JSONArray();
		
		if(showType.equals("movie")) {
			List<ShowPreview> resList = Repository.getInstance().searchMovie(name, ratingType, country, genres);
			arr = getAsJson(resList);
		} else if(showType.equals("series")) {
			
		} else {
			
		}
		
		response.getWriter().println(arr);
	}
	
	private JSONArray getAsJson(List<ShowPreview> shows) {
		JSONArray arr = new JSONArray();
		
		for(ShowPreview show: shows) {
			JSONObject obj = new JSONObject();
			obj.put("id", show.getId());
			obj.put("name", show.getName());
			obj.put("releasedate", show.getReleaseDate().toString());
			obj.put("imdbrating", show.getImdbRating());
			obj.put("rottenorangerating", show.getRottenTomatoRating());
			obj.put("showfandomrating", show.getShowFandomRating());
			obj.put("posterimgurl", show.getPosterImgUrl());
			obj.put("ratingType", show.getRatingType());
			
			arr.add(obj);
		}
		
		return arr;
	}
}





