package com.moviefandom.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.moviefandom.repository.Repository;

@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		
		if(session != null) {
			String review = request.getParameter("review");
			int movieId = Integer.parseInt(request.getParameter("movieid"));
			int rating = Integer.parseInt(request.getParameter("rating"));
			String email = (String) session.getAttribute("email");
			LocalDateTime timestamp = LocalDateTime.now();
			
			boolean isPosted = Repository.getInstance().postReviews(movieId, email, review, rating, timestamp);
			
			if(isPosted) {
				out.println("{'isPosted': true, 'status':'success'}");
			} else {
				out.println("{'isPosted': false, 'status':'Something went wrong'}");
			}
		} else {
			out.println("'status':'not logged in'}");
		}
	}

}
