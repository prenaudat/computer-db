package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * @author paulr_000
 *
 */
public enum ComputerDAO {
	INSTANCE;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASS = "qwerty1234";
	private Map<Integer, Integer> cache = new ConcurrentHashMap<>();
	private static DateTimeFormatter getTimestampformatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm");

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
	public Computer getComputerById(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn
					.prepareStatement("SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.id=?;");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				long resId = rs.getLong("id");
				String resName = rs.getString("name");
				Timestamp resIntroduced = rs.getTimestamp("introduced");
				Timestamp resDiscontinued = rs.getTimestamp("discontinued");
				LocalDateTime introduced;
				if (resIntroduced != null) {
					introduced = resIntroduced.toLocalDateTime();
				} else {
					introduced = null;
				}
				LocalDateTime discontinued;
				if (resDiscontinued != null) {
					discontinued = resIntroduced.toLocalDateTime();
				} else {
					discontinued = null;
				}
				Company company = new Company(rs.getLong("company_id"),
						rs.getString("company_name"));
				return new Computer(resId, resName, introduced, discontinued,
						company);
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			close(stmt, conn);
		}

	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> getComputersByCompanyId(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn
					.prepareStatement("cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id where cmp.id=?;");
			stmt.setLong(1, id);
			final ResultSet rs = stmt.executeQuery();
			List<Computer> computerList = new ArrayList<Computer>();
			long resId;
			String resName;
			Timestamp resIntroduced;
			Timestamp resDiscontinued;
			while (rs.next()) {
				resId = rs.getLong("id");
				resName = rs.getString("name");
				resIntroduced = rs.getTimestamp("introduced");
				resDiscontinued = rs.getTimestamp("discontinued");
				LocalDateTime introduced;
				if (resIntroduced != null) {
					introduced = resIntroduced.toLocalDateTime();
				} else {
					introduced = null;
				}
				LocalDateTime discontinued;
				if (resDiscontinued != null) {
					discontinued = resIntroduced.toLocalDateTime();
				} else {
					discontinued = null;
				}
				Company company = new Company(rs.getLong("company_id"),
						rs.getString("company_name"));
				computerList.add(new Computer(resId, resName, introduced,
						discontinued, company));
			}
			return computerList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(stmt, conn);
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
	public void updateComputer(Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			stmt.setLong(4, computer.getCompany().getId());
			stmt.setLong(5, computer.getId());
			stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}
	}

	/**
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @throws SQLException
	 */
	public void save(Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn
					.prepareStatement("Insert into computer(id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?);");
			stmt.setLong(1, computer.getId());
			stmt.setString(2, computer.getName());
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getIntroduced()));
			stmt.setTimestamp(4, Timestamp.valueOf(computer.getDiscontinued()));
			stmt.setLong(5, computer.getCompany().getId());
			stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

	}

	/**
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> getComputerList(int currentIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn
					.prepareStatement("select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;");
			stmt.setInt(1, currentIndex);
			stmt.setInt(2, pageSize);
			final ResultSet rs = stmt.executeQuery();
			List<Computer> computerList = new ArrayList<Computer>();
			Computer.ComputerBuilder c = new Computer.ComputerBuilder();
			while (rs.next()) {
				c.id(rs.getLong("id")).name(rs.getString("name"));
				if (rs.getTimestamp("introduced") != null) {
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(stmt, conn);
		}
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public void remove(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("DELETE FROM computer WHERE id=?;");
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

	}

	/**
	 * @param stmt
	 * @param conn
	 * @throws SQLException
	 */
	public void close(PreparedStatement stmt, Connection conn) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
