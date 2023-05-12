package com.moviefandom.servlets.watchlist;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.moviefandom.repository.Repository;

/**
 * Servlet implementation class GetWatchListTagServlet
 */
@WebServlet("/GetWatchListTagServlet")
public class GetWatchListTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		String username = (String) httpSession.getAttribute("username");
		
		List<String> watchListTags = Repository.getInstance().getWatchListTags(username);
		
		response.getWriter().println(toJsonArray(watchListTags));
	}
	
	private JSONArray toJsonArray(List<String> watchListTags) {
		JSONArray jsonArray = new JSONArray();
		
		for(String tag: watchListTags)
			jsonArray.add(tag);
		
		return jsonArray;
	}

}
