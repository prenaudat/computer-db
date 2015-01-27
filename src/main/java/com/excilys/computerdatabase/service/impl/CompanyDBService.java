package com.excilys.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.dao.impl.CompanyDAO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyDBServiceInterface;

/**
 * @author excilys
 *
 */
@Service("companyService")
public class CompanyDBService implements CompanyDBServiceInterface {
	@Autowired
	CompanyDAO companyDAO;
	ConnectionManager connectionmanager = ConnectionManager.getInstance();

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

	@Override
	public void remove(long id) {
		companyDAO.remove(id);
	}
}
