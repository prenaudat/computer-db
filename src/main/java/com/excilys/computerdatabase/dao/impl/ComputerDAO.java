package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.excilys.computerdatabase.dao.ComputerDAOInterface;
import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.mapper.row.impl.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author excilys
 *
 */
public enum ComputerDAO implements ComputerDAOInterface {
	// The instance of the ComputerDAO singleton
	INSTANCE;
	private ConnectionManager connectionManager = ConnectionManager
			.getInstance();
	// Static Queries and Updates to be prepared
	private static final String SINGLE_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.id=?;";
	private static final String LIST_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id AS company_id, cmp.name AS company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;";
	private static final String INSERT_STMT = "INSERT into computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private static final String UPDATE_STMT = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String DELETE_STMT = "DELETE FROM computer WHERE id=?;";
	private static final String COUNT_STMT = "SELECT COUNT(id) FROM computer;";
	ComputerMapper computerMapper = new ComputerMapper();
	private static int pageSize = 10;

	// Logger for this class
	// private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	/**
	 * Return instance of Singleton ComputerDAO
	 * 
	 * @return returns instance of ComputerDAO
	 */
	public static ComputerDAO getInstance() {
		try {
			return INSTANCE;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieve a single computer identified by its unique ID
	 * 
	 * @param id
	 *            ID of computer to get
	 * @return Computer corresponding to ID
	 */
	public Computer get(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(SINGLE_QUERY_STMT);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			return computerMapper.mapRow(rs);

		} catch (SQLException e) {
			// logger.warn("Error selecting id=" + id);
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn, rs);
		}
	}

	/**
	 * Update an existing computer
	 * 
	 * @param id
	 *            Id of computer to update
	 * @param name
	 *            new name for computer
	 * @param introduced
	 *            new introduction date for computer
	 * @param discontinued
	 *            new discontinuation date for computer
	 * @param companyId
	 *            new company ID for computer
	 */
	public void update(final Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(UPDATE_STMT);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2,
					Timestamp.valueOf(computer.getIntroduced().atStartOfDay()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()
					.atStartOfDay()));
			if (computer.getCompany() != null) {
				stmt.setLong(4, computer.getCompany().getId());
			} else {
				stmt.setLong(4, 0);
			}
			stmt.setLong(5, computer.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// logger.warn("Error updating id=" + computer.getId());
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	/**
	 * Insert a new computer into the database
	 * 
	 * @param name
	 *            Name for new computer
	 * @param introduced
	 *            Introduction date for new Computer
	 * @param discontinued
	 *            Discontinuation deate for new computer
	 * @param companyId
	 *            Company ID for new COmputer
	 */
	public int save(final Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(INSERT_STMT,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2,
					Timestamp.valueOf(computer.getIntroduced().atStartOfDay()));
			if (computer.getDiscontinued() != null) {
				stmt.setTimestamp(3, Timestamp.valueOf(computer
						.getDiscontinued().atStartOfDay()));
			}
			stmt.setLong(4, computer.getCompany().getId());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// logger.warn("Error saving computer" + computer);
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn, rs);
		}
	}

	/**
	 * Retrieve a list of computers corresponding to the selection
	 * 
	 * @param pageNumber
	 *            index of Page to return
	 * @return Page of computers
	 * @throws SQLException
	 */
	public Page getPage(int pageNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(LIST_QUERY_STMT,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, pageNumber * pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			List<Computer> computerList = computerMapper.mapRowList(rs);
			Page page = new Page();
			page.setPageNumber(pageNumber);
			page.setList(computerList);
			page.setCount(getCount());
			page.setPageCount((int) Math.ceil(page.getCount() / pageSize));
			return page;
		} catch (SQLException e) {
			// logger.warn("Error retrieving ids=[ %d-%d  ]", pageNumber
			// * pageSize, (pageNumber + 1) * pageSize);
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn, rs);
		}
	}

	/**
	 * Delete a computer from database
	 * 
	 * @param id ID of computer to delete
	 * @throws SQLException
	 */
	public void remove(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(DELETE_STMT);
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// logger.warn("Error removing id=[ %d=", id);
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	/**
	 * Get count of Computers 
	 * @return number of computers
	 */
	public int getCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();//
			stmt = conn.prepareStatement(COUNT_STMT);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int numberOfRows = rs.getInt(1);
				return numberOfRows;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// logger.warn("Error counting rows");
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}
}
