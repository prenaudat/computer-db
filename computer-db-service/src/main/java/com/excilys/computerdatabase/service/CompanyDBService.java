package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.core.model.Company;

/**
 * @author excilys
 *
 */
public interface CompanyDBService {

	public Company get(long id);

	public void delete(long id);
	
	public List<Company> getAll();

}
