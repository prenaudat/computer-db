package com.excilys.computerdatabase.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapper;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.Validator;

/**
 * Servlet redirects to after populating JSP with computer List
 * 
 * @author excilys
 */
@Controller
public class DashBoard {
	@Autowired
	ComputerDBService computerDBService;
	ComputerDTOMapper computerDTOMapper = new ComputerDTOMapper();

	/**
	 * Map GET requests to /computers.
	 * @param allRequestParams : URL parameters concerning the page
	 * @return ModelAndView of Dashboard
	 */
	@RequestMapping(value = "/computers", method = RequestMethod.GET)
	protected ModelAndView get(
			@RequestParam Map<String, String> allRequestParams) {
		return sendHomePage(allRequestParams);
	}

	/**
	 * Map POST requests to /computers for deletion. 
	 * @param allRequestParams CSV type : 1,2,3,4,5 values corresponding to values of IDs to be separated
	 * @return ModelAndView of dash board
	 */
	@RequestMapping(value = "/computers", method = RequestMethod.POST)
	protected ModelAndView post(
			@RequestParam Map<String, String> allRequestParams) {
		Arrays.asList(allRequestParams.get("selection").split(",")).forEach(
				i -> computerDBService.remove(Validator.validateInt(i)));
		return sendHomePage(allRequestParams);
	}

	/**
	 * Return home page Model after populating with computers from ComputerDBService
	 * @param allRequestParams All request parameters.
	 * @return ModelAndView of dashboard. 
	 */
	protected ModelAndView sendHomePage(Map<String, String> allRequestParams) {
		Page page = Validator.validateParameterList(allRequestParams);
		page.setList(computerDBService.getPage(page).getList());
		page.setTarget("computers");
		return new ModelAndView("dashboard", "page", page);
	}
}
