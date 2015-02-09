package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;
import com.excilys.computerdatabase.validator.ComputerDTOValidator;

/**
 * Manage /addComputer Display and persist computer for adding
 * 
 * @author excilys
 *
 */
@Controller
public class AddComputer {
	@Autowired
	ComputerDBServiceImpl computerDBService;
	@Autowired
	CompanyDBServiceImpl companyDBService;
	ComputerDTOMapperImpl computerDTOMapper = new ComputerDTOMapperImpl();

	@InitBinder("computerDTO")
	protected void initComputerDTOBinder(WebDataBinder binder) {
		binder.setValidator(new ComputerDTOValidator());
	}

    /**
     * Maps GET requests on /computer/add
     * @return ModelAndView of AddComputer Page, populating company list with Options
     */
    @RequestMapping(value="/computers/add", method = RequestMethod.GET)
	protected ModelAndView doGet(){
		return new ModelAndView("addComputer", "companies", companyDBService.getAll());
	}

    /**
     * Map PSOT requests on /computer/add 
     * @param req 
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="/computers/add", method = RequestMethod.POST)
	protected ModelAndView doPost(@Valid ComputerDTO dto, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return doGet();
		}
		computerDBService.save(computerDTOMapper.mapFromDTO(dto));
		ModelAndView home = new ModelAndView(new RedirectView("/computers",
				true));
		home.addObject("page", computerDBService.getPage(0));
		return home;
	}

}
