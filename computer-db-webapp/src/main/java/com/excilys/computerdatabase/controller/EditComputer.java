package com.excilys.computerdatabase.controller;

import java.util.ArrayList;

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
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.computerdatabase.binding.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.binding.dto.validation.ComputerDTOValidator;
import com.excilys.computerdatabase.core.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

/**
 * Edit computer controller. Maps /editComputer url
 * 
 * @author excilys
 *
 */
@Controller
public class EditComputer {
	@Autowired
	ComputerDBServiceImpl computerDBService;
	@Autowired
	CompanyDBServiceImpl companyDBService;
	ComputerDTOMapperImpl computerDTOMapper = new ComputerDTOMapperImpl();

	/**
	 * Initialize and bind ComputerDTOValidator
	 * 
	 * @param binder
	 */
	@InitBinder("computerDTO")
	protected void initComputerDTOBinder(WebDataBinder binder) {
		binder.setValidator(new ComputerDTOValidator());
	}

	/**
	 * Request mapping for /computers/edit and return page to edit computer with
	 * corresponding id
	 * 
	 * @param id
	 *            id of computer to edit
	 * @return ModelAndView of EditComputer Page
	 */
	@RequestMapping(value = "/computers/edit", method = RequestMethod.GET)
	protected ModelAndView get(@RequestParam(value = "id") Long id) {
		return sendEditPage(id);
	}

	/**
	 * Map post requests on /computers/edit and either accept or send back to
	 * form.
	 * 
	 * @param dto
	 *            the computer from the form
	 * @param bindingResult
	 *            Validator results
	 * @return ModelAndView of edit or home page in case of success
	 */
	@RequestMapping(value = "/computers/edit", method = RequestMethod.POST)
	protected ModelAndView doPost(@Valid ComputerDTO dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("editComputer", "computer", dto);
			mav.addObject("id", dto.getId());
			ArrayList<String> errorCodes = new ArrayList<>();
			bindingResult.getAllErrors().forEach(
					c -> errorCodes.add(c.getCode().toString()));
			mav.getModel().put("companies", companyDBService.findAll());
			mav.getModel().put("computer", dto);
			mav.getModel().put("errors", errorCodes);
			return mav;
		}
		Computer c = computerDTOMapper.mapFromDTO(dto);
		if(c.getCompany().getId()==0){
			c.setCompany(null);
		}
		computerDBService.save(c);
		ModelAndView home = new ModelAndView(new RedirectView("/computers",
				true));
		// home.addObject("page", computerDBService.getPage(0));
		return home;
	}

	/**
	 * 
	 * @param id
	 *            ID of computer to edit
	 * @return ModelAndView of editComputer with corresponding computer.
	 */
	private ModelAndView sendEditPage(Long id) {
		ModelAndView editPage = new ModelAndView("editComputer");
		editPage.addObject("computer",
				computerDTOMapper.mapToDTO(computerDBService.findOne(id)));
		editPage.addObject("companies", companyDBService.findAll());
		return editPage;
	}

}