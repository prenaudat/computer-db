package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author excilys
 *
 */
public interface ComputerDBServiceInterface {
	public Page getPage(int pageNumber);

	public Computer get(final long id);

	public void remove(final long id);

	public void update(final Computer computer);

	public void save(final Computer computer);
	
	public int getCount();
}
