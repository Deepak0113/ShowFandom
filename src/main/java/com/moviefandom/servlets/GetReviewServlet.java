package com.moviefandom.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.moviefandom.dto.Review;
import com.moviefandom.repository.Repository;

/**
 * Servlet implementation class GetReviewServlet
 */
@WebServlet("/GetReviewServlet")
public class GetReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int movieid = Integer.parseInt(request.getParameter("movieid"));
		List<Review> reviews = Repository.getInstance().getReviews(movieid);
		System.out.println("total review: " + reviews.size());
		
		JSONArray array = getJson(reviews);
		out.println(array);
	}
	
	private JSONArray getJson(List<Review> reviews) {
		JSONArray jsonArray = new JSONArray();
		
		for(Review review: reviews) {
			JSONObject obj = new JSONObject();
			obj.put("name", review.getName());
			obj.put("id", review.getId());
			obj.put("rating", review.getRating());
			obj.put("review", review.getReview());
			obj.put("timestamp", review.getTimestamp());
			jsonArray.add(obj);
		}
		
		return jsonArray;
	}

}
