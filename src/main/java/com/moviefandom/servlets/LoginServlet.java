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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.moviefandom.dto.User;
import com.moviefandom.repository.Repository;
import com.moviefandom.statuscall.UserStatus;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		
		UserStatus userStatus = Repository.getInstance().loginUser(username, password);
		PrintWriter out = response.getWriter();
		
		
		if(userStatus.getStatus().equals("USER-NOTEXIST")) {
			out.println("{\"status\":\"USER-NOTEXIST\",\"message\":\"User does not exist\"}");
		} else {
			User user = userStatus.getUser();
			
			if(user.isBlocked()){
				out.println("{\"status\":\"BLOCKED-USER\",\"message\":\"Your account is blocked\"}");
			} else {
				HttpSession session = request.getSession();
				
				session.setAttribute("username", user.getUsername());
				session.setAttribute("email", user.getEmailid());
				session.setAttribute("name", user.getName());
				session.setAttribute("isblocked", user.isBlocked());
				
				out.println(getUserJson(user));
			}
		}
		
	}
	
	private JSONObject getUserJson(User user) {
		JSONObject obj = new JSONObject();
		obj.put("status", "SUCCESS-LOGGEDIN");
		obj.put("username", user.getUsername());
		obj.put("email", user.getEmailid());
		obj.put("name", user.getName());
		obj.put("isblocked", user.isBlocked());
		
		return obj;
	}
}
