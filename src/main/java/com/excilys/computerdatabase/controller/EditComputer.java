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

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	ComputerDBService computerDBService;
	CompanyDBService companyDBService;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		computerDBService = new ComputerDBService();
		companyDBService = new CompanyDBService();
		resp.setContentType("text/html");
		String idParam = req.getParameter("id");
		Long id;
		if (Validator.isValidNumber(idParam)) {
			id = Long.parseLong(idParam);
			req.setAttribute("computer", computerDBService.get(id));
			req.setAttribute("companies", companyDBService.getAll());
			req.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
					.forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/views/404.html").forward(req,
					resp);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		computerDBService = new ComputerDBService();
		Computer.ComputerBuilder c = new Computer.ComputerBuilder();
		if(Validator.isValidNumber(req.getParameter("id"))){
			c.id(Long.parseLong(req.getParameter("id")));
		}
		if(Validator.isValidString(req.getParameter("name"))){
			c.name(req.getParameter("name"));
		}
		if(Validator.isValidDate(req.getParameter("introduced"))){
				c.introduced(LocalDate.parse(req.getParameter("introduced")));
		}
		if(Validator.isValidDate(req.getParameter("discontinued"))){
				c.discontinued(LocalDate.parse(req.getParameter("discontinued")));
		}
		if(Validator.isValidNumber(req.getParameter("company_id"))){
			c.company(new Company.CompanyBuilder().id(Long.parseLong(req.getParameter("company_id"))).build());
		}
		computerDBService.update(c.build());
		
			}
}