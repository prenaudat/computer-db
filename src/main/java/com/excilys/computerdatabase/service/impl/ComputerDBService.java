package com.excilys.computerdatabase.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.dao.impl.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ComputerDBServiceInterface;

/**
 * @author paulr_000
 *
 */
public class ComputerDBService implements ComputerDBServiceInterface {
	ComputerDAO computerDAO = ComputerDAO.getInstance();
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
	 * @return
	 * @throws SQLException
	 */
	public Computer get(final long id) {
		return computerDAO.get(id);
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

	public void save(final Computer computer) {
		computerDAO.save(computer);
	}

	@Override
	public int getCount() {
		return computerDAO.getCount();
	}
}
