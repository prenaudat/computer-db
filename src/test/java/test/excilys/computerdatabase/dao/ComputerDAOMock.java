package test.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.computerdatabase.dao.ConnectionManager;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.mapper.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Page;

@RunWith(MockitoJUnitRunner.class)
public class ComputerDAOMock {
	private ConnectionManager connectionManager = ConnectionManager
			.getInstance();
	private ComputerDTOMapperImpl computerDTOMapper = new ComputerDTOMapperImpl();
	private static final String SINGLE_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.id=?;";
	private static final String LIST_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id AS company_id, cmp.name AS company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;";
	private static final String INSERT_STMT = "INSERT into computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private static final String UPDATE_STMT = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String DELETE_STMT = "DELETE FROM computer WHERE id=?;";
	private static final String COUNT_STMT = "SELECT COUNT(id) FROM computer;";

	private static int pageSize = 10;

	public Computer get(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(SINGLE_QUERY_STMT);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Computer.Builder cb = new Computer.Builder();
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

		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

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
			stmt.setLong(4, computer.getCompany().getId());
			stmt.setLong(5, computer.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	/**
	 * Insert a new computer into the database
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @throws SQLException
	 */
	public int save(final Computer computer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(INSERT_STMT,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2,
					Timestamp.valueOf(computer.getIntroduced().atStartOfDay()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()
					.atStartOfDay()));
			stmt.setLong(4, computer.getCompany().getId());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	/**
	 * Retrieve a list of computers corresponding to the selection
	 * 
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page getPage(int pageNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(LIST_QUERY_STMT,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, pageNumber * pageSize);
			stmt.setInt(2, pageSize);
			final ResultSet rs = stmt.executeQuery();
			List<Computer> computerList = new ArrayList<Computer>();
			Computer.Builder c = new Computer.Builder();
			while (rs.next()) {
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
			Page page = new Page();
			page.setPageNumber(pageNumber);
			page.setList(computerDTOMapper.mapToDTO(computerList));
			page.setCount(getCount());
			page.setPageCount((int) Math.ceil(page.getCount() / pageSize));
			return page;
		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	/**
	 * Delete a computer from database
	 * 
	 * @param id
	 * @return
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
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

	public int getCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(COUNT_STMT);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int numberOfRows = rs.getInt(1);
				return numberOfRows;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}

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
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}
}
