package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyDAOInterface {
	public Company get(long id);

	public List<Company> getPage(int pageNumber);

	public int save(String name);

	public void update(long id, String name);
}
