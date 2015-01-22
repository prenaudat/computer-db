package com.excilys.computerdatabase.mapper.row.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.mapper.row.RowMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * Map a computer from a Row
 * @author excilys
 *
 */
public class ComputerMapper implements RowMapper<Computer> {

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.RowMapper#mapRow(java.sql.ResultSet)
	 */
	@Override
	public Computer mapRow(ResultSet rs) {
		Computer.Builder cb = new Computer.Builder();
		try{
		if (rs.next()) {
			cb.id(rs.getLong("id"));
			cb.name(rs.getString("name"));
			Timestamp resIntroduced = rs.getTimestamp("introduced");
			Timestamp resDiscontinued = rs.getTimestamp("discontinued");
			if (resIntroduced != null) {
				cb.introduced(resIntroduced.toLocalDateTime().toLocalDate());
			} else {
				cb.introduced(null);
			}
			if (resDiscontinued != null) {
				cb.discontinued(resDiscontinued.toLocalDateTime()
						.toLocalDate());
			} else {
				cb.discontinued(null);
			}
			cb.company(new Company(rs.getLong("company_id"), rs
					.getString("company_name")));
		}
		return cb.build();
		}catch(SQLException e){
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.RowMapper#mapRowList(java.sql.ResultSet)
	 */
	@Override
	public List<Computer> mapRowList(ResultSet rs) {
		List<Computer> computerList = new ArrayList<Computer>();
		try{
		while (rs.next()) {
			Computer.Builder c = new Computer.Builder();
			c.id(rs.getLong("id")).name(rs.getString("name"));
			if (rs.getTimestamp("introduced") != null) {
				c.introduced(rs.getTimestamp("introduced")
						.toLocalDateTime().toLocalDate());
			} else {
				c.introduced(null);
			}
			if (rs.getTimestamp("discontinued") != null) {
				c.discontinued(rs.getTimestamp("discontinued")
						.toLocalDateTime().toLocalDate());
			} else {
				c.discontinued(null);
			}
			computerList.add(c.company(
					new Company.CompanyBuilder()
							.id(rs.getLong("company_id"))
							.name(rs.getString("company_name")).build())
					.build());
		}
		return computerList;
		}catch(SQLException e){
			return null;
		}
	}

}
