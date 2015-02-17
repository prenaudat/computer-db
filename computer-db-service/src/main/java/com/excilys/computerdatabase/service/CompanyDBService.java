package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.core.model.Company;

/**
 * @author excilys
 *
 */
public interface CompanyDBService {

	public Company findOne(long id);

	public void delete(long id);
	
	public List<Company> findAll();
	
	public boolean exists(long id);

}
