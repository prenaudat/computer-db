package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.model.Company;

/**
 * @author paulr_000 d
 */
// Computer Database Access object..
public enum CompanyDAO {
	// Singleton pattern
	INSTANCE;
	static final Logger LOG = LoggerFactory.getLogger(CompanyDAO.class);
	private static final String UPDATE_STMT = "UPDATE company SET name=? WHERE id=? ;";
	private static final String LIST_QUERY_STMT = "SELECT * FROM company LIMIT ?,?;";
	private static final String INSERT_STMT = "INSERT INTO company(name) VALUES (?);";
	private static final String SINGLE_QUERY_STMT = "SELECT * FROM company WHERE id =?";

	/**
	 * @return
	 */
	public static CompanyDAO getInstance() {
		return INSTANCE;
	}

	/**
	 * @param id
	 * @return Return one company
	 */
	public Company get(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(SINGLE_QUERY_STMT);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Company(rs.getLong("id"), rs.getString("name"));
			} else {
				return null;// Display values
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionManager.close(stmt, conn);
		}

	}

	/**
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 */
	public List<Company> getList(int currentIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(LIST_QUERY_STMT);
			stmt.setInt(1, currentIndex);
			stmt.setInt(2, pageSize);
			final ResultSet rs = stmt.executeQuery();
			ArrayList<Company> companyList = new ArrayList<Company>();
			while (rs.next()) {
				companyList.add(new Company(rs.getLong("id"), rs
						.getString("name")));
			}
			return companyList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionManager.close(stmt, conn);
		}

	}

	/**
	 * @param name
	 */
	public void save(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(INSERT_STMT);
			stmt.setString(1, name);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(stmt, conn);
		}
	}

	/**
	 * @param id
	 * @param name
	 */
	public void update(long id, String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
			stmt = conn.prepareStatement(UPDATE_STMT);
			stmt.setString(1, name);
			stmt.setLong(2, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(stmt, conn);
		}
	}

}