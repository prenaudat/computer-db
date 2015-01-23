package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author excilys
 *
 */
public interface ComputerDBServiceInterface {
	/**
	 * @param pageNumber
	 * @return
	 */
	public Page getPage(int pageNumber);

	/**
	 * @param id
	 * @return
	 */
	public Computer get(final long id);

	/**
	 * @param id
	 */
	public void remove(final long id);

	/**
	 * @param computer
	 */
	public void update(final Computer computer);

	/**
	 * @param computer
	 */
	public void save(final Computer computer);

	/**
	 * @param query
	 * @return
	 */
	public int getCount(String query);

	/**
	 * @param conn
	 * @param id
	 */
	public void removeByCompany(long id);

}
