package com.moviefandom.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moviefandom.repository.Repository;
import com.moviefandom.statuscall.UserStatus;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserStatus userStatus = Repository.getInstance().signupUser(name, username, email, password);
		PrintWriter out = response.getWriter();
		
		switch(userStatus.getStatus()) {
			case "SUCCESS-ADDED" -> out.println("{\"status\":\"SUCCESS-ADDED\", \"message\":\"signed in successfully\"}");
			case "DUPLICATE-USERNAME" -> out.println("{\"status\":\"success\", \"message\":\"Username already exists\"}");
			case "DUPLICATE-EMAIL" -> out.println("{\"status\":\"success\", \"message\":\"Email already exists\"}");
		}
	}

}
