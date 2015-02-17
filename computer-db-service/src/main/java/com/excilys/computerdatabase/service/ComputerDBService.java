package com.excilys.computerdatabase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excilys.computerdatabase.core.model.Computer;

/**
 * @author excilys
 *
 */
public interface ComputerDBService {

	/**
	 * @param id
	 * @return
	 */
	public Computer findOne(final long id);

	/**
	 * @param computer
	 */
	public void save(final Computer computer);

	/**
	 * @param conn
	 * @param id
	 */
	public void removeByCompany(long id);

	/**
	 * @param id
	 */
	public void delete(String id);

	/**
	 * @param pageable
	 * @param search
	 * @return
	 */
	public Page<Computer> retrievePage(Pageable pageable, String search);

	public boolean exists(long id);
}
