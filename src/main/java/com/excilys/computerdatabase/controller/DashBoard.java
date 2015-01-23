package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapper;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * @author excilys
 * Servlet redirects home to Dashboard after populating JSP with computer List
 */
@WebServlet("/computers")
public class DashBoard extends HttpServlet {
	ComputerDBService computerDBService = new ComputerDBService();
	ComputerDTOMapper computerDTOMapper = new ComputerDTOMapper();
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
		Page page =	Validator.validateParameterList(paramList);
		page.setTarget("computers"); //because we are currently on home page
		return page;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Arrays.asList(req.getParameter("selection").split(",")).forEach(i -> computerDBService.remove(Long.parseLong(i)));
		resp.sendRedirect("computers");
	}
}
