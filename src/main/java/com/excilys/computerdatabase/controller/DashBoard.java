package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.pagination.OrderBy;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * @author excilys
 * Servlet redirects home to Dashboard after populating JSP with computer List
 */
@WebServlet("/home")
public class DashBoard extends HttpServlet {
	ComputerDBService computerDBService;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		computerDBService = new ComputerDBService();
		resp.setContentType("text/html");
		String pageParam = req.getParameter("page");
		if (Validator.isValidNumber(pageParam)) {
			pageNumber = Integer.parseInt(pageParam);
		}
		if (pageNumber > Math.ceil((int) computerDBService.getCount()
				/ pageSize)) {
			req.setAttribute("page", computerDBService.getPage(0));
		} else {
			Page page = computerDBService.getPage(pageNumber);
			page.setSize(size);
			req.setAttribute("page", computerDBService.getPage(pageNumber));
		}
		req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req,
				resp);
	}
	
	private Page initPage(ComputerDBService computerDBService, HttpServletRequest req){
		Map<String, String[]> paramList = req.getParameterMap();
		if(paramList.containsKey("size")){
			String sizeString =	 req.getParameter("size");
		}
		if(paramList.containsKey("page")){
			String pageString =	 req.getParameter("page");
		}		
		if(paramList.containsKey("sort")){
			String sort =	 req.getParameter("sort");
		}
		if(paramList.containsKey("order")){
			String order =	 req.getParameter("order");
		}
		if(paramList.containsKey("query")){
			String query =	 req.getParameter("query");
		}
		String target ="home"; //because we are currently on home page
		return new Page.Builder().pageNumber(Integer.parseInt(pageString)).orderBy(OrderBy.valueOf(order)).query(query).target(target).build();
	}
}
