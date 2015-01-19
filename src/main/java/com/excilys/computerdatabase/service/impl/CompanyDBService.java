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
	/**
	 * @param currentCompanyPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Company> getPage(int pageNumber) {
		return CompanyDAO.getInstance().getPage(pageNumber);
	}

	public Company get(long id) {
		return CompanyDAO.getInstance().get(id);
	}

	public void save(String name) {
		CompanyDAO.getInstance().save(name);
	}

	public void update(long id, String name) {
		CompanyDAO.getInstance().update(id, name);
	}

	public List<Company> getAll() {
		return CompanyDAO.getInstance().getAll();
	}
}
