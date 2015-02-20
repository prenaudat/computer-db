package com.excilys.computerdatabase.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.excilys.computerdatabase.binding.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.core.common.OrderBy;
import com.excilys.computerdatabase.core.common.RequestPage;
import com.excilys.computerdatabase.core.model.Computer;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

/**
 * Servlet redirects to after populating JSP with computer List
 * 
 * @author excilys
 */
@Controller
public class DashBoard {
	@Autowired
	ComputerDBServiceImpl computerDBService;
	ComputerDTOMapperImpl computerDTOMapper = new ComputerDTOMapperImpl();
	private static final Logger LOGGER = LoggerFactory.getLogger(DashBoard.class);

	/**
	 * Map GET requests to /computers.
	 * 
	 * @param allRequestParams
	 *            : URL parameters concerning the page
	 * @return ModelAndView of Dashboard
	 */
	@RequestMapping(value = "/computers", method = RequestMethod.GET)
	protected ModelAndView get(
			@RequestParam Map<String, String> allRequestParams) {
		LOGGER.info("Dashboard queried with request parameters : {}", Arrays.toString((allRequestParams.entrySet().toArray())));
		return sendHomePage(allRequestParams);
	}

	/**
	 * Map POST requests to /computers for deletion.
	 * 
	 * @param allRequestParams
	 *            CSV type : 1,2,3,4,5 values corresponding to values of IDs to
	 *            be separated
	 * @return ModelAndView of dash board
	 */
	@RequestMapping(value = "/computers", method = RequestMethod.POST)
	protected ModelAndView post(
			@RequestParam Map<String, String> allRequestParams) {
		LOGGER.info("Dashboard deletion feature queried for values : {}", Arrays.toString((allRequestParams.entrySet().toArray())));
		Arrays.asList(allRequestParams.get("selection").split(",")).forEach(
				i -> computerDBService.delete(i));
		return sendHomePage(allRequestParams);
	}

	/**
	 * Return home page Model after populating with computers from
	 * ComputerDBService
	 * 
	 * @param allRequestParams
	 *            All request parameters.
	 * @return ModelAndView of dashboard.
	 */
	protected ModelAndView sendHomePage(Map<String, String> allRequestParams) {
		RequestPage page = generatePage(allRequestParams);
		ModelAndView mav = new ModelAndView("dashboard");
		String query = null;
		if (allRequestParams.containsKey("query")) {
			query = allRequestParams.get("query");
		}
		Page<Computer> p = computerDBService.retrievePage(page, query);
		mav.addObject("query", HtmlUtils.htmlEscape(query, "UTF-8"));
		mav.addObject("target", "computers");
		mav.addObject("list", computerDTOMapper.mapToDTO(p.getContent()));
		mav.addObject("size", p.getSize());
		if (page.getOrderBy() != null) {
			mav.addObject("orderBy", page.getOrderBy());
		}
		mav.addObject("computerCount", p.getTotalElements());
		mav.addObject("pageCount", p.getTotalPages());
		mav.addObject("pageNumber", p.getNumber());
		mav.addObject("size", page.getPageSize());
		return mav;
	}

	/**
	 * Generate a ComputerPage from request parameters
	 * @param allRequestParams Map<K,V> with page parameters
	 * @return
	 */
	protected RequestPage generatePage(Map<String, String> allRequestParams) {
		int page = 0;
		int size = 10;
		OrderBy orderBy;
		if (allRequestParams.containsKey("size")
				&& GenericValidator.isInt(allRequestParams.get("size"))) {
			size = Integer.parseInt(allRequestParams.get("size"));
		}
		if (allRequestParams.containsKey("page")
				&& GenericValidator.isInt(allRequestParams.get("page"))) {
			page = Integer.parseInt(allRequestParams.get("page"));
		}
		RequestPage p = new RequestPage(page, size);

		if (allRequestParams.containsKey("orderBy")) {
			try {
				orderBy = OrderBy.valueOf(allRequestParams.get("orderBy"));
			} catch (IllegalArgumentException e) {
				orderBy = OrderBy.ID_ASC;
			}
		} else {
			orderBy = OrderBy.ID_ASC;
		}
		p.setOrderBy(orderBy);
		return p;
	}
}
