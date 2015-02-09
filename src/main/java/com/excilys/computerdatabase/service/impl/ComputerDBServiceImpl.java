package com.excilys.computerdatabase.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.dao.impl.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.OrderBy;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.pagination.Sort;
import com.excilys.computerdatabase.service.ComputerDBService;

/**
 * @author paulr_000
 *
 */
@Service("computerService")
public class ComputerDBServiceImpl implements ComputerDBService {
	@Autowired
	ComputerDAO computerDAO;
	@Autowired
	CompanyDAO companyDAO;

	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
			;
	 * @throws SQLException
	 */
	public Page getPage(int pageNumber) {
		return computerDAO.getPage(pageNumber);
	}

	/**
	 * @param id
	 * @return Computer corresponding to ID
	 * 
	 * @throws SQLException
	 */
	public Computer get(final long id) {
		return computerDAO.get(id);
	}

	/**
	 * Return count of computerDAO
	 * 
	 * @param query
	 *            : query for which results should be counted
	 * @return the count.
	 */
	@Override
	public int getCount(String query) {
		return computerDAO.getCount(query);
	}

	/**
	 * Return page according to its criteria.
	 * 
	 * @param page
	 * @return
	 */
	public Page getPage(Page page) {
		return computerDAO.getPage(page);
	}

	/**
	 * @param id
	 * @param name
	 * @param introducedDate
	 * @param discontinuedDate
	 * @param cid
	 * @throws SQLException
	 */
	public void update(final Computer computer) {
		computerDAO.update(computer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilisIntys.computerdatabase.service.ComputerDBServiceInterface#
	 * save( com.excilys.computerdatabase.model.Computer)
	 */
	public void save(final Computer computer) {
		computerDAO.save(computer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.ComputerDBServiceInterface#
	 * removeByCompany(long)
	 */
	@Transactional
	public void removeByCompany(long id) {
		computerDAO.removeByCompany(id);
		boolean a = true;
		if (a) {
			throw new RuntimeException("Rollback this transaction!");
		}
		companyDAO.remove(id);
	}
	/**
	 * @param i
	 * @return
	 */
	public void remove(String id) {
		if (GenericValidator.isLong(id)) {
			computerDAO.remove(Long.parseLong(id));
		}
	}

	public Page generatePage(Map<String, String> allRequestParams) {
		Page page = new Page();
		if(allRequestParams.containsKey("size") && GenericValidator.isInt(allRequestParams.get("size"))){
			page.setSize(Integer.parseInt(allRequestParams.get("size")));
		}		
		if(allRequestParams.containsKey("page") && GenericValidator.isInt(allRequestParams.get("page"))){
			page.setPageNumber(Integer.parseInt(allRequestParams.get("page")));
		}		
		if(allRequestParams.containsKey("sort")){
			page.setSort(Sort.valueOf(allRequestParams.get("sort").toUpperCase()));
		}
		if(allRequestParams.containsKey("order")){
			page.setOrderBy(OrderBy.valueOf(allRequestParams.get("order").toUpperCase()));
		}
		if(allRequestParams.containsKey("query")){
			page.setQuery(allRequestParams.get("query"));
		}
		else{
			page.setQuery("");
		}
		return page;

	}
}
