package com.excilys.computerdatabase.mapper.row.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.mapper.row.RowMapper;
import com.excilys.computerdatabase.model.Company;

/**
 * Map a Company from a row
 * @author excilys
 *
 */
public class CompanyMapper implements RowMapper<Company> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.RowMapper#mapRow(java.sql.ResultSet)
	 */
	@Override
	public Company mapRow(ResultSet rs) {
		try {
			rs.next();
			return new Company(rs.getLong("id"), rs.getString("name"));
		} catch (SQLException e) {
			throw new PersistenceException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.mapper.RowMapper#mapRowList(java.sql.ResultSet
	 * )
	 */
	@Override
	public List<Company> mapRowList(ResultSet rs) {
		List<Company> companyList = new ArrayList<Company>();
		try {
			while (rs.next()) {
				companyList.add(new Company(rs.getLong("id"), rs
						.getString("name")));
			}
			return companyList;
		} catch (SQLException e) {
			throw new PersistenceException();
		}
	}

}
