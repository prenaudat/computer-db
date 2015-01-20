package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBService;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	ComputerDBService computerDBService;
	CompanyDBService companyDBService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		computerDBService = new ComputerDBService();
		companyDBService = new CompanyDBService();
		resp.setContentType("text/html");

		req.setAttribute("companies", companyDBService.getAll());
		req.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req,
				resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		computerDBService = new ComputerDBService();
		Computer.ComputerBuilder c = new Computer.ComputerBuilder();
		if (Validator.isValidString(req.getParameter("name"))) {
			c.name(req.getParameter("name"));
		}
		if (Validator.isValidDate(req.getParameter("introduced"))) {
			c.introduced(LocalDate.parse(req.getParameter("introduced")));
		}
		if (Validator.isValidDate(req.getParameter("discontinued"))) {
			c.discontinued(LocalDate.parse(req.getParameter("discontinued")));
		}
		if (Validator.isValidNumber(req.getParameter("company_id"))) {
			c.company(new Company.CompanyBuilder().id(
					Long.parseLong(req.getParameter("company_id"))).build());
		}
		System.out.println(c.build());
		computerDBService.save(c.build());
		resp.sendRedirect("home");
	}

}
