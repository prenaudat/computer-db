package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

/**
 * CompanyDAO Interface
 * @author excilys
 *
 */
public interface CompanyDAOInterface {
	public Company get(long id);

	public List<Company> getPage(int pageNumber);

	public int save(String name);

	public void update(long id, String name);
	
	public void remove(Connection conn, long id);
}
