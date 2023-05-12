package com.moviefandom.servlets.friends;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.moviefandom.repository.Repository;
import com.moviefandom.statuscall.FriendStatus;
import com.moviefandom.statuscall.Status;

/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendId = request.getParameter("friendid");
		HttpSession session = request.getSession(false);
		JSONObject obj = new JSONObject();
		
		String username = (String) session.getAttribute("username");
		Status friendStatus = Repository.getInstance().addFriend(friendId, username);
		obj.put("status", friendStatus.getStatus());
		
		switch(friendStatus.getStatus()) {
			case "FRIEND-ADDED" -> obj.put("message", "Started following " + friendId);
			case "DUPLICATE" -> obj.put("message", "Already following " + friendId);
			case "USER-NOT-EXIST" -> obj.put("message", "User not exist " + friendId);
		}
		
		response.getWriter().print(obj);;
	}

}
