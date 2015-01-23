package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.ComputerDAOInterface;
import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapper;
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
	private static final String COUNT_STMT = "SELECT COUNT(cpt.id) FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.name LIKE ? OR cmp.name LIKE ?";
	private static final String PAGE_QUERY = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.name LIKE ? OR cmp.name LIKE ? ";
	private static final String DELETE_COMPANY_COMPUTERS = "DELETE FROM computer WHERE company_id=?";
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id=?";
	ComputerMapper computerMapper = new ComputerMapper();
	ComputerDTOMapper computerDTOMapper = new ComputerDTOMapper();
	private static int pageSize = 10;

	// Logger for this class
	private Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

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
			LOGGER.warn("Error selecting id=" + id);
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
			System.out.println(computer.getIntroduced());
			if (computer.getIntroduced() != null) {
				stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()
						.atStartOfDay()));
			} else {
				stmt.setNull(2, java.sql.Types.TIMESTAMP);
			}
			if (computer.getDiscontinued() != null) {
				stmt.setTimestamp(3, Timestamp.valueOf(computer
						.getDiscontinued().atStartOfDay()));
			} else {
				stmt.setNull(3, java.sql.Types.TIMESTAMP);
			}
			if (computer.getCompany() != null) {
				stmt.setLong(4, computer.getCompany().getId());
			} else {
				stmt.setLong(4, 0);
			}
			stmt.setLong(5, computer.getId());
			System.out.println(stmt);
			stmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warn("Error updating id=" + computer.getId());
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
	 *            Discontinuation date for new computer
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
			if (computer.getIntroduced() != null) {
				stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()
						.atStartOfDay()));
			} else {
				stmt.setNull(2, java.sql.Types.TIMESTAMP);
			}
			if (computer.getDiscontinued() != null) {
				stmt.setTimestamp(3, Timestamp.valueOf(computer
						.getDiscontinued().atStartOfDay()));
			} else {
				stmt.setNull(3, java.sql.Types.TIMESTAMP);
			}
			stmt.setLong(4, computer.getCompany().getId());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			LOGGER.warn("Error saving computer" + computer);
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
			page.setList(computerDTOMapper.mapToDTO(computerList));
			page.setPageNumber(pageNumber);
			page.setCount(getCount(page.getQuery()));
			page.setPageCount((int) Math.ceil(page.getCount() / pageSize));
			return page;
		} catch (SQLException e) {
			LOGGER.warn("Error retrieving ids=[ %d-%d  ]", pageNumber
			* pageSize, (pageNumber + 1) * pageSize);
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn, rs);
		}
	}

	/**
	 * Delete a computer from database
	 * 
	 * @param id
	 *            ID of computer to delete
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
			LOGGER.warn("Error removing id=[ %d=", id);
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	/**
	 * Removes a company and all its associated computers
	 * 
	 * @param id
	 *            ID of company to delete, along with its computers
	 */
	public void removeByCompany(final long id, Connection conn) {
		PreparedStatement cptStmt = null;
		PreparedStatement cmpStmt = null;
		try {
			cptStmt = conn.prepareStatement(DELETE_COMPANY_COMPUTERS);
			cmpStmt = conn.prepareStatement(DELETE_COMPANY);
			cptStmt.setLong(1, id);
			cmpStmt.setLong(1, id);
			cptStmt.executeUpdate();
			cmpStmt.executeUpdate();
			cmpStmt.close();
			cptStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get count of Computers
	 * 
	 * @return number of computers
	 */
	public int getCount(String query) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(COUNT_STMT,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int numberOfRows = rs.getInt(1);
				return numberOfRows;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			LOGGER.warn("Error counting rows");
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	public Page getPage(Page page) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			String query = PAGE_QUERY + "ORDER BY "
					+ page.getOrderBy().toString() + " "
					+ page.getSort().toString() + " LIMIT ? , ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + page.getQuery() + "%");
			stmt.setString(2, "%" + page.getQuery() + "%");
			stmt.setInt(3, page.getPageNumber() * page.getSize());
			stmt.setInt(4, page.getSize());
			ResultSet rs = stmt.executeQuery();
			List<Computer> computerList = computerMapper.mapRowList(rs);
			page.setList(computerDTOMapper.mapToDTO(computerList));
			page.setCount(getCount(page.getQuery()));
			page.setPageCount(page.getCount() / page.getSize());
			return page;
		} catch (SQLException e) {
			LOGGER.warn("Error counting rows");
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}
}
