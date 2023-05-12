package com.moviefandom.servlets.friends;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.moviefandom.repository.Repository;

/**
 * Servlet implementation class GetFriendsCountServlet
 */
@WebServlet("/GetFriendsCountServlet")
public class GetFriendsCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		JSONObject obj = new JSONObject();
		
		int followersCount = Repository.getInstance().getTotalFollowers(username);
		int followingCount = Repository.getInstance().getTotalFollowing(username);
		
		obj.put("followersCount", followersCount);
		obj.put("followingCount", followingCount);
		
		response.getWriter().print(obj);
	}

}
