package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excilys.computerdatabase.core.model.Computer;

/**
 * Computer CRUD Service
 * 
 * @author excilys
 *
 */
public interface ComputerDBService {

	/**
	 * Find a computer by id
	 * 
	 * @param id to query
	 * @return Computer 
	 */
	public Computer findOne(final long id);

	/**
	 * Upsert a computer
	 * 
	 * @param computer  to be persisted
	 */
	public void save(final Computer computer);

	/**
	 * Remove a computer by id
	 * 
	 * @param id of company
	 */
	public void removeByCompany(long id);

	/**
	 * Delete by String id
	 * 
	 * @param id Id
	 */
	public void delete(String id);

	/**
	 * Delete by Long id
	 * 
	 * @param id Id of computer
	 */
	public void delete(long id);

	/**
	 * Get page of computers
	 * 
	 * @param pageable   contains page parameters
	 * @param search   Search query
	 * @return Page of computers
	 */
	public Page<Computer> retrievePage(Pageable pageable, String search);

	/**
	 * Check if computer exists
	 * 
	 * @param id   of computer
	 * @return True or false
	 */
	public boolean exists(long id);

	/**
	 * Get list of all computers
	 * 
	 * @return list of all computers
	 */
	public List<Computer> findAll();
}
