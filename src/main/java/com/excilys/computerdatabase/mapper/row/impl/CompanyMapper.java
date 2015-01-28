package com.excilys.computerdatabase.mapper.row.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.model.Company;

/**
 * Map a Company from a row
 * @author excilys
 *
 */
public class CompanyMapper implements RowMapper<Company> {
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Company(rs.getLong("id"), rs.getString("name"));
	}

}
