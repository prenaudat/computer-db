package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.model.Company;

/**
 * @author paulr_000
 *
 */
public class CompanyDBService {
	/**
	 * @param currentCompanyPageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Company> getList(int currentCompanyPageIndex, int pageSize){
		return CompanyDAO.getInstance().getList(currentCompanyPageIndex, pageSize);
	}
}
