package com.moviefandom.servlets.friends;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moviefandom.repository.Repository;

/**
 * Servlet implementation class RemoveFriendServlet
 */
@WebServlet("/RemoveFriendServlet")
public class RemoveFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession(false).getAttribute("username");
		String friendid = request.getParameter("friendid");
		
		boolean isRemoved = Repository.getInstance().removeFollowing(username, friendid); 
		response.getWriter().println(isRemoved ? "REMOVED" : "SOMETHING-WENT-WRONG");
	}

}
