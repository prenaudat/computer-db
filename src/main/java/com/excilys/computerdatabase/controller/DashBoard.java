package com.excilys.computerdatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

@WebServlet("/home")
public class DashBoard extends HttpServlet {
	ComputerDBService computerDBService;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		computerDBService = new ComputerDBService();
		int pageNumber=0;
		resp.setContentType("text/html");
		String pageParam=req.getParameter("page");
		if(Validator.isValidNumber(pageParam)){
			pageNumber=Integer.parseInt(pageParam);
		}
		req.setAttribute("computers", computerDBService.getPage(pageNumber));
		req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req,
				resp);
	}
}
