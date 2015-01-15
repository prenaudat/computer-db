package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public interface CompanyDAOInterface {
	public Company get(long id);
	public List<Company> getList(int currentIndex, int pageSize);
	public int save(String name);
	public void update(long id, String name);
}
