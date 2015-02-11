package com.excilys.computerdatabase.dao;

import java.sql.Connection;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.ComputerPage;

/**
 * Interface for ComputerDAO
 * 
 * @author excilys
 */
public interface ComputerDAOInterface {

	/**
	 * Return a computer
	 * 
	 * @param id
	 *            Idetifies the computer
	 * @return
	 */
	public Computer get(final long id);

	/**
	 * Update a computer
	 * 
	 * @param computer
	 *            Computer to be updated
	 */
	public void update(final Computer computer);

	/**
	 * Persist a computer
	 * 
	 * @param computer
	 *            the computer to be saved
	 */
	public void save(final Computer computer);

	/**
	 * Get a page by index
	 * 
	 * @param pageNumber
	 *            Number of the page
	 * @return return Page containing list of computers
	 */
	public ComputerPage getPage(int pageNumber);

	/**
	 * Delete a computer by ID
	 * 
	 * @param id
	 *            ID of computer to delete
	 */
	public void remove(final long id);

	/**
	 * @param query
	 */
	public int getCount(String query);

	/**
	 * @param page
	 * @return
	 */
	public ComputerPage getPage(ComputerPage page);

	public void removeByCompany(long id);
}
