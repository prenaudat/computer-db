package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.dao.AbstractController;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBService;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * Manage /addComputer Display and persist computer for adding
 * 
 * @author excilys
 *
 */
@Controller
public class AddComputer {
	@Autowired
	ComputerDBService computerDBService;
	@Autowired
	CompanyDBService companyDBService;

    @RequestMapping(value="/computers/add", method = RequestMethod.GET)
	protected ModelAndView doGet(){
		return new ModelAndView("addComputer", "companies", companyDBService.getAll());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
    @RequestMapping(value="/computers/add", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Call computerDBService
		Computer.Builder c = new Computer.Builder();
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
		resp.sendRedirect("../computers");
	}

}
