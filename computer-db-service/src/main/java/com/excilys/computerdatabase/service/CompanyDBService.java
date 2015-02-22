package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.core.model.Company;

/**
 * Company Service interface
 * 
 * @author excilys
 *
 */
public interface CompanyDBService {

	/**
	 * Find company by id
	 * 
	 * @param id Company id
	 * @return COmpany
	 */
	public Company findOne(long id);

	/**
	 * Delete a company by id. Transactional for computer deletions delete by id
	 * 
	 * @param id Company id
	 */
	public void delete(long id);

	/**
	 * @return List Company of all companies
	 */
	public List<Company> findAll();

	/**
	 * Check if Company exists
	 * 
	 * @param id
	 *            id to query
	 * @return True or false
	 */
	public boolean exists(long id);

}
