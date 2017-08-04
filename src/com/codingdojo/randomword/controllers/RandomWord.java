package com.codingdojo.randomword.controllers;

import java.io.IOException;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class RandomWord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int count;
		if (session.getAttribute("count") == null) {
			count = 0;
			session.setAttribute("count", count);
		} else {
			count = (int) session.getAttribute("count");
		}
		request.setAttribute("count", count);
		
		String word;
		if (session.getAttribute("word") == null) {
			word = "";
			session.setAttribute("word", word);
		} else {
			word = (String) session.getAttribute("word");
		}
		request.setAttribute("word", word);
		
		String date = "";
		if (session.getAttribute("date") == null) {
			session.setAttribute("date", date);
		} else {
			date = (String) session.getAttribute("date");
		}
		request.setAttribute("date", date);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/randomword.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int count = (int) session.getAttribute("count");
		count++;
		session.setAttribute("count", count);
		
		Random rand = new Random();
		String word = "";
		for (int i = 1; i <= 10; i++) {
			word += Character.toString((char)(rand.nextInt(26) + 'a'));
		}
		session.setAttribute("word", word);

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy - h:m a");
		Date date = new Date();
		session.setAttribute("date", dateFormat.format(date));
		
		response.sendRedirect("/RandomWord");
	}

}
