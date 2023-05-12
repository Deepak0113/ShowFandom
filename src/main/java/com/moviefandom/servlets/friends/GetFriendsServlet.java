package com.moviefandom.servlets.friends;

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


@WebServlet("/GetFriendsServlet")
public class GetFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendType = request.getParameter("friendtype");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		List<String> friends;
		
		if(friendType.equals("following")) {
			friends = Repository.getInstance().getFollowing(username);
		} else {
			friends = Repository.getInstance().getFollowers(username);
		}
		
		
		JSONArray arr = new JSONArray();
		arr.addAll(friends);
		
		response.getWriter().print(arr);
	}

}
