package test.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.computerdatabase.dao.CompanyDAOInterface;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.model.Company;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDAOMock implements CompanyDAOInterface {
	private static final String UPDATE_STMT = "UPDATE company SET name=? WHERE id=? ;";
	private static final String LIST_QUERY_STMT = "SELECT * FROM company LIMIT ?,?;";
	private static final String INSERT_STMT = "INSERT INTO company(name) VALUES (?);";
	private static final String SINGLE_QUERY_STMT = "SELECT * FROM company WHERE id =?";
	ConnectionManagerTest connectionManager = ConnectionManagerTest
			.getInstance();

	@Override
	public Company get(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(SINGLE_QUERY_STMT);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Company(rs.getLong("id"), rs.getString("name"));
			} else {
				return null;// Display values
			}
		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
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
			conn = connectionManager.getConnection();
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
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}

	}

	/**
	 * @param name
	 */
	public int save(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(INSERT_STMT,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
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
	 * @param id
	 * @param name
	 */
	public void update(long id, String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connectionManager.getConnection();
			stmt = conn.prepareStatement(UPDATE_STMT);
			stmt.setString(1, name);
			stmt.setLong(2, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException();
		} finally {
			connectionManager.close(stmt, conn);
		}
	}
}
