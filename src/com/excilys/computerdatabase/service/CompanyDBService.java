package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.model.Company;

/**
 * @author paulr_000
 *
 */
public class CompanyDBService {
	/**
	 * @return
	 * @throws SQLException
	 */
	public int count() throws SQLException{
		return CompanyDAO.getInstance().count();
	}
	/**
	 * @param currentCompanyPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Company> getCompanyList(int currentCompanyPageIndex, int pageSize) throws SQLException{
		return CompanyDAO.getInstance().getCompanyList(currentCompanyPageIndex, pageSize);
	}
}
