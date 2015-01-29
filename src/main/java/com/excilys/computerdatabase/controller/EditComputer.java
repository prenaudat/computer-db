package com.excilys.computerdatabase.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapper;
import com.excilys.computerdatabase.service.impl.CompanyDBService;
import com.excilys.computerdatabase.service.impl.ComputerDBService;
import com.excilys.computerdatabase.validator.ComputerDTOValidator;

/**
 * Edit computer controller. Maps /editComputer url
 * 
 * @author excilys
 *
 */
@Controller
public class EditComputer {
	@Autowired
	ComputerDBService computerDBService;
	@Autowired
	CompanyDBService companyDBService;
	ComputerDTOMapper computerDTOMapper = new ComputerDTOMapper();

	@InitBinder("computerDTO")
	protected void initComputerDTOBinder(WebDataBinder binder) {
		binder.setValidator(new ComputerDTOValidator());
	}

	@RequestMapping(value = "/computers/edit", method = RequestMethod.GET)
	protected ModelAndView get(@RequestParam(value = "id") Long id) {
		return sendEditPage(id);
	}

	@RequestMapping(value = "/computers/edit", method = RequestMethod.POST)
	protected ModelAndView doPost(@Valid ComputerDTO dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = sendEditPage(dto.getId());
			mav.addObject("errors", bindingResult.getAllErrors());
			mav.addObject("companies", companyDBService.getAll());
			return new ModelAndView("editComputer", "computer", dto);
		}
		computerDBService.update(computerDTOMapper.mapFromDTO(dto));
		return new ModelAndView("dashboard", "page", computerDBService.getPage(0));
	}

	private ModelAndView sendEditPage(Long id) {
		ModelAndView editPage = new ModelAndView("editComputer");
		editPage.addObject("computer", computerDBService.get(id));
		editPage.addObject("companies", companyDBService.getAll());
		return editPage;
	}

}