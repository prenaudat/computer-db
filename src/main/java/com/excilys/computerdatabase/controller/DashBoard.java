package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * @author excilys
 * Servlet redirects home to Dashboard after populating JSP with computer List
 */
@WebServlet("/home")
public class DashBoard extends HttpServlet {
	ComputerDBService computerDBService = new ComputerDBService();
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		Page page = initPage(req);
		page = computerDBService.getPage(page);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req,
				resp);
	}
	
	/**
	 * Initialize page from URL parameters
	 * @param req
	 * @return
	 */
	private Page initPage(HttpServletRequest req){
		Map<String, String[]> paramList = req.getParameterMap();
		for(String[] x : paramList.values()){
			System.out.println(x[0]);
		}
		Page page =	Validator.validateParameterList(paramList);
		page.setTarget("home"); //because we are currently on home page
		return page;
	}
}
