package com.excilys.computerdatabase.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.computerdatabase.binding.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.binding.dto.validation.ComputerDTOValidator;
import com.excilys.computerdatabase.core.model.Computer;
import com.excilys.computerdatabase.service.CompanyDBService;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

/**
 * Servlet managing /addComputer Display and persistign computers for adding
 * 
 * @author excilys
 *
 */
@Controller
public class AddComputer {
	@Autowired
	private ComputerDBServiceImpl computerDBService;
	@Autowired
	private CompanyDBService companyDBService;
	private ComputerDTOMapperImpl computerDTOMapper = new ComputerDTOMapperImpl();
	private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

	/**
	 * ComputerDTOValidator binding
	 * 
	 * @param binder
	 */
	@InitBinder("computerDTO")
	protected void initComputerDTOBinder(WebDataBinder binder) {
		binder.setValidator(new ComputerDTOValidator());
	}

	/**
	 * Maps GET requests on /computer/add
	 * 
	 * @return ModelAndView of AddComputer Page, populating company list with
	 *         Options
	 */
	@RequestMapping(value = "/computers/add", method = RequestMethod.GET)
	protected ModelAndView doGet() {
		return new ModelAndView("addComputer", "companies",
				companyDBService.findAll());
	}

	/**
	 * Map POST requests to create computers
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return ModelAndView of dashboard in case of success. Else return page
	 *         w/errors
	 */
	@RequestMapping(value = "/computers/add", method = RequestMethod.POST)
	protected ModelAndView doPost(@Valid ComputerDTO dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Erros detected : {}", bindingResult.getAllErrors());
			ModelAndView mav = new ModelAndView("addComputer", "computer", dto);
			ArrayList<String> errorCodes = new ArrayList<>();
			bindingResult.getAllErrors().forEach(
					c -> errorCodes.add(c.getCode().toString()));
			mav.getModel().put("companies", companyDBService.findAll());
			mav.getModel().put("computer", dto);
			mav.getModel().put("errors", errorCodes);
			return mav;
		}
		Computer c = computerDTOMapper.mapFromDTO(dto);
		if (c.getCompany().getId() == 0) {
			c.setCompany(null);
		}
		computerDBService.save(c);
		ModelAndView home = new ModelAndView(new RedirectView("/computers",
				true));
		return home;
	}
}
