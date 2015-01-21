package com.excilys.computerdatabase.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyDBServiceInterface;

/**
 * @author paulr_000
 *
 */
public class CompanyDBService implements CompanyDBServiceInterface {
	CompanyDAO companyDAO = CompanyDAO.getInstance();
	/**
	 * @param currentCompanyPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Company> getPage(int pageNumber) {
		return companyDAO.getPage(pageNumber);
	}

	public Company get(long id) {
		return companyDAO.get(id);
	}

	public void save(String name) {
		companyDAO.save(name);
	}

	public void update(long id, String name) {
		companyDAO.update(id, name);
	}

	public List<Company> getAll() {
		return companyDAO.getAll();
	}
}
