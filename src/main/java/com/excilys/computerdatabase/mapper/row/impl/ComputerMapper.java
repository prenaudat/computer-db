package com.excilys.computerdatabase.mapper.row.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * Map a computer from a Row
 * @author excilys
 *
 */
public class ComputerMapper implements RowMapper<Computer> {
	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer.Builder cb = new Computer.Builder();
			cb.id(rs.getLong("id"));
			cb.name(rs.getString("name"));
			Timestamp resIntroduced = rs.getTimestamp("introduced");
			Timestamp resDiscontinued = rs.getTimestamp("discontinued");
			if (resIntroduced != null) {
				cb.introduced(resIntroduced.toLocalDateTime().toLocalDate());
			} else {
				cb.introduced((LocalDate) null);
			}
			if (resDiscontinued != null) {
				cb.discontinued(resDiscontinued.toLocalDateTime()
						.toLocalDate());
			} else {
				cb.discontinued((LocalDate) null);
			}
			cb.company(new Company(rs.getLong("company_id"), rs
					.getString("company_name")));
		return cb.build();
		}

}
