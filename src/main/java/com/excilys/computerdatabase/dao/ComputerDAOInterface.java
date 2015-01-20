package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author excilys Interface for ComputerDAO
 */
public interface ComputerDAOInterface {

	/**
	 * @param id
	 * @return
	 */
	public Computer get(final long id);

	/**
	 * @param computer
	 */
	public void update(final Computer computer);

	/**
	 * @param computer
	 */
	public int save(final Computer computer);

	/**
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 */
	public Page getPage(int pagenNumber);

	/**
	 * @param id
	 */
	public void remove(final long id);

}
