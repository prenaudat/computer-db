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
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
@RunWith(MockitoJUnitRunner.class)
public class ComputerDAOMock {
	ConnectionManagerTest connectionManager = ConnectionManagerTest.getInstance();
	private static final String SINGLE_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id as company_id, cmp.name as company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id WHERE cpt.id=?;";
	private static final String LIST_QUERY_STMT = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cmp.id AS company_id, cmp.name AS company_name FROM computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;";
	private static final String INSERT_STMT = "INSERT into computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private static final String UPDATE_STMT = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String DELETE_STMT = "DELETE FROM computer WHERE id=?;";
	public Computer get(final long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(SINGLE_QUERY_STMT);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Computer.ComputerBuilder cb = new Computer.ComputerBuilder();
			if (rs.next()) {
				cb.id(rs.getLong("id"));
				cb.name(rs.getString("name"));
				Timestamp resIntroduced = rs.getTimestamp("introduced");
				Timestamp resDiscontinued = rs.getTimestamp("discontinued");
				if (resIntroduced != null) {
					cb.introduced(resIntroduced.toLocalDateTime());
				} else {
					cb.introduced(null);	
				}
				if (resDiscontinued != null) {
					cb.discontinued(resDiscontinued.toLocalDateTime());
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
			stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
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
			stmt = conn.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
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
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> getList(final int currentIndex, final int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
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
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}
	 /**
		 * Delete a computer from database
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
}
