package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.computerdatabase.dao.AbstractController;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBService;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * Edit computer controller. Maps /editComputer url
 * 
 * @author excilys
 *
 */
@Controller
@WebServlet("/computers/edit")
public class EditComputer extends AbstractController {
	@Autowired
	ComputerDBService computerDBService;
	@Autowired
	CompanyDBService companyDBService;
    private ServletContext context;
    private ServletConfig config;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Computer.Builder c = new Computer.Builder();
		if (Validator.isValidNumber(req.getParameter("id"))) {
			c.id(Long.parseLong(req.getParameter("id")));
		}
		if (Validator.isValidString(req.getParameter("name"))) {
			c.name(req.getParameter("name"));
		}
		if (Validator.isValidDate(req.getParameter("introduced"))
				|| req.getParameter("introduced") == null) {
			c.introduced(LocalDate.parse(req.getParameter("introduced")));
		}
		if (Validator.isValidDate(req.getParameter("discontinued"))
				|| req.getParameter("discontinued") == null) {
			c.discontinued(LocalDate.parse(req.getParameter("discontinued")));
		}
		if (Validator.isValidNumber(req.getParameter("company_id"))
				|| req.getParameter("introduced") == null) {
			c.company(new Company.CompanyBuilder().id(
					Long.parseLong(req.getParameter("company_id"))).build());
		}
		computerDBService.update(c.build());
		resp.sendRedirect("../computers");

	}
}