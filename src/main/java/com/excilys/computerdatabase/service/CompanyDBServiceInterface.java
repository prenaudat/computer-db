package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.model.Company;
/**
 * @author paulr_000
 *
 */
public interface CompanyDBServiceInterface {
	public List<Company> getList(int currentCompanyPageIndex, int pageSize);
	public Company get(long id);
	public void save(String name);
	public void update(long id,String name);
}
