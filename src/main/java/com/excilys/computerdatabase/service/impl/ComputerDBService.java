package com.excilys.computerdatabase.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.dao.impl.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ComputerDBServiceInterface;

/**
 * @author paulr_000
 *
 */
@Service("computerService")
public class ComputerDBService implements ComputerDBServiceInterface {
	@Autowired
	ComputerDAO computerDAO;
	@Autowired
	CompanyDAO companyDAO;

	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
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
	 * @throws SQLException
	 */
	public void remove(final long id) {
		computerDAO.remove(id);
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
	 * com.excilys.computerdatabase.service.ComputerDBServiceInterface#save(
	 * com.excilys.computerdatabase.model.Computer)
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
}
