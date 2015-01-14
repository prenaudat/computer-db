package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.cli.Client;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * @author paulr_000
 *
 */
public enum ComputerDAO {
	INSTANCE;
	static final Logger LOG = LoggerFactory.getLogger(ComputerDAO.class);
	static final String SINGLE_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.id=?;";
	static final String LIST_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id AS company_id, cmp.name AS company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;";
	static final String SINGLE_QUERY_BY_COMPANY_ID_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id AS company_id, cmp.name AS company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cmp.id=?;";
	static final String INSERT_STMT = "INSERT into computer(id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?);";
	static final String UPDATE_STMT = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	static final String DELETE_STMT = "DELETE FROM computer WHERE id=?;";

	/**
	 * @return
	 */
	public static ComputerDAO getInstance() {
		return INSTANCE;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Computer get(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(SINGLE_QUERY_STMT);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Computer.ComputerBuilder cb = new Computer.ComputerBuilder();
			if (rs.next()) {
				cb.id(rs.getLong("id"));
				cb.name(rs.getString("name"));
				Timestamp resIntroduced = rs.getTimestamp("introduced");
				Timestamp resDiscontinued = rs.getTimestamp("discontinued");
				LocalDateTime introduced;
				if (resIntroduced != null) {
					cb.introduced(resIntroduced.toLocalDateTime());
				} else {
					cb.introduced(null);
				}
				LocalDateTime discontinued;
				if (resDiscontinued != null) {
					cb.discontinued(resIntroduced.toLocalDateTime());
				} else {
					cb.discontinued(null);
				}
				cb.company(new Company(rs.getLong("company_id"), rs
						.getString("company_name")));
			}
			return cb.build();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionManager.close(stmt, conn);
		}

	}

	/**
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @throws SQLException
	 */
	public void updateComputer(final Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(UPDATE_STMT);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			stmt.setLong(4, computer.getCompany().getId());
			stmt.setLong(5, computer.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(stmt, conn);
		}
	}

	/**
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @throws SQLException
	 */
	public void save(final Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(INSERT_STMT);
			stmt.setLong(1, computer.getId());
			stmt.setString(2, computer.getName());
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getIntroduced()));
			stmt.setTimestamp(4, Timestamp.valueOf(computer.getDiscontinued()));
			stmt.setLong(5, computer.getCompany().getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(stmt, conn);
		}

	}

	/**
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> getList(final int currentIndex, final int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(LIST_QUERY_STMT);
			stmt.setInt(1, currentIndex);
			stmt.setInt(2, pageSize);
			final ResultSet rs = stmt.executeQuery();
			List<Computer> computerList = new ArrayList<Computer>();
			Computer.ComputerBuilder c = new Computer.ComputerBuilder();
			while (rs.next()) {
				c.id(rs.getLong("id")).name(rs.getString("name"));
				if (rs.getTimestamp("introduced") != null
						){
					c.introduced(rs.getTimestamp("introduced")
							.toLocalDateTime());
				} else {
					c.introduced(null);
				}
				if (rs.getTimestamp("discontinued") != null) {
					c.discontinued(rs.getTimestamp("discontinued")
							.toLocalDateTime());
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionManager.close(stmt, conn);
		}
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public void remove(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(DELETE_STMT);
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(stmt, conn);
		}

	}

}
