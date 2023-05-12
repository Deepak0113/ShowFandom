package com.moviefandom.servlets.home.load;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.moviefandom.repository.Repository;


@WebServlet("/GetLoadDataServlet")
public class GetLoadDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String showType = request.getParameter("showtype");
		
		List<String> genres = showType.equals("anime") ?
				Repository.getInstance().getAnimeGenres() : Repository.getInstance().getGenres();
		List<String> ratingTypes = Repository.getInstance().getRatingType();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("genre", getAsJsonArray(genres));
		jsonObject.put("ratingtypes", getAsJsonArray(ratingTypes));
		
		System.out.println(showType);
		System.out.println(showType.equals("movie"));
		
		
		if(showType.equals("movie")) {
			List<String> countries = Repository.getInstance().getCountriesMovies();
			jsonObject.put("countries", getAsJsonArray(countries));
		} else if(showType.equals("anime")) {
			List<String> countries = Repository.getInstance().getCountriesAnime();
			jsonObject.put("countries", getAsJsonArray(countries));
		} else {
			List<String> countries = Repository.getInstance().getCountriesSeries();
			jsonObject.put("countries", getAsJsonArray(countries));
		}		
		
		response.getWriter().print(jsonObject);
	}
	
	private JSONArray getAsJsonArray(List<String> list) {
		JSONArray jsonArray = new JSONArray();
		
		for(String item: list)
			jsonArray.add(item);
		
		return jsonArray;
	}

}
