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
	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page getPage(int pageNumber) {
		return ComputerDAO.getInstance().getPage(pageNumber);
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Computer get(final long id) {
		return ComputerDAO.getInstance().get(id);
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
	public void remove(final long id) {
		ComputerDAO.getInstance().remove(id);
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
		ComputerDAO.getInstance().update(computer);
	}

	public void save(final Computer computer) {
		ComputerDAO.getInstance().save(computer);
	}

	@Override
	public int getCount() {
		return ComputerDAO.getInstance().getCount();
	}
}
