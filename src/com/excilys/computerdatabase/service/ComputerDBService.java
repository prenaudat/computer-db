package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;

/**
 * @author paulr_000
 *
 */
public class ComputerDBService {
	/**
	 * @param currentComputerPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> getComputerList(final int currentComputerPageIndex,
			final int pageSize) {
		return ComputerDAO.getInstance().getComputerList(
				currentComputerPageIndex, pageSize);
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Computer getComputerById(final long id) {
		return ComputerDAO.getInstance().getComputerById(id);
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
	public void updateComputer(final Computer computer) {
		ComputerDAO.getInstance().updateComputer(computer);
	}

	public void save(final Computer computer) {
		ComputerDAO.getInstance().save(computer);
	}
}
