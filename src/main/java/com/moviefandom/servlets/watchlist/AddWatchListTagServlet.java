package com.moviefandom.servlets.watchlist;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.moviefandom.repository.Repository;

/**
 * Servlet implementation class AddWatchListTagServlet
 */
@WebServlet("/AddWatchListTagServlet")
public class AddWatchListTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		String username = (String) httpSession.getAttribute("username");
		String tagName = request.getParameter("tagName");
		System.out.println(tagName);
		
		boolean isAdded = Repository.getInstance().addWatchListTags(tagName, username);
		response.getWriter().println(isAdded);
	}

}
