package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

/**
 * @author excilys
 *Interface for ComputerDAO 
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
	public void updateComputer(final Computer computer);
	
	/**
	 * @param computer
	 */
	public int save(final Computer computer);
	
	/**
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 */
	public List<Computer> getList(final int currentIndex, final int pageSize);
	
	/**
	 * @param id
	 */
	public void remove(final long id);
	
}
