package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	public static ComputerDAO getInstance(){
		return INSTANCE;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Computer getComputerById(long id) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id where cpt.id=?;");
			stmt.setLong(1, id);
			//String sql = "select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id where cpt.id="
			//+ id + ";";
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

				long companyId = rs.getLong("company_id");
				return new Computer(resId, resName, introduced, discontinued,
						companyId);
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			close(stmt,conn);
		}

	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Computer> getComputersByCompanyId(final long id) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id where cmp.id=?;");;
			stmt.setLong(1, id);
			final ResultSet rs = stmt.executeQuery();
			ArrayList<Computer> computerList = new ArrayList<Computer>();
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
				long companyId = rs.getLong("company_id");
				computerList.add(new Computer(resId, resName, introduced,
						discontinued, companyId));
			}
			return computerList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			close(stmt,conn);

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
	public void updateComputer(long id, String name, Timestamp introduced, Timestamp discontinued, int companyId) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			//String sql = "UPDATE computer SET name='"+name+"', introduced='"+introduced+"', discontinued='"+discontinued+"', company_id="+companyId+" WHERE id="+id+";";
			stmt.setString(1, name);
			stmt.setTimestamp(2, introduced);
			stmt.setTimestamp(3, discontinued);
			stmt.setLong(4, companyId);
			stmt.setLong(5, id);
			stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(stmt,conn);
		}

	}

	/**
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @throws SQLException
	 */
	public void createComputer(String name, Timestamp introduced, Timestamp discontinued, int companyId) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("Insert into computer(name,introduced, discontinued, company_id) VALUES (?,?,?,?);");
			//String sql = "Insert into computer(name,introduced, discontinued, company_id) VALUES ('"+name+"', '"+introduced+"','"+discontinued+"', "+companyId+");";
			stmt.setString(1, name);
			stmt.setTimestamp(2, introduced);
			stmt.setTimestamp(3, discontinued);
			stmt.setLong(4, companyId);
			stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(stmt,conn);
		}

	}
	/**
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Computer> getComputerList(int currentIndex, int pageSize) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT ? , ?;");
			//final String sql = "select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT "+currentIndex+" , "+pageSize+";";
			stmt.setInt(1, currentIndex);
			stmt.setInt(2, pageSize);
			final ResultSet rs = stmt.executeQuery();
			ArrayList<Computer> computerList = new ArrayList<Computer>();
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
					discontinued = resDiscontinued.toLocalDateTime();
				} else {
					discontinued = null;
				}
				long companyId = rs.getLong("company_id");
				computerList.add(new Computer(resId, resName, introduced,
						discontinued, companyId));
			}
			return computerList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			close(stmt,conn);
		}
	}
	/**
	 * @return
	 * @throws SQLException
	 */
	public int count() throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("select COUNT(id) as rowcount from computer;");
			//			String sql = "select COUNT(id) as rowcount from computer;";
			stmt.executeQuery();
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt("rowcount");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			close(stmt,conn);
		}

	}
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int count(long id) throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("select COUNT(id) as rowcount from computer where company_id =?;");
			stmt.setLong(1,id);
			//String sql = "select COUNT(id) as rowcount from computer where company_id =" + id + ";";
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt("rowcount");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			close(stmt,conn);
		}

	}
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public void remove(long id) throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("DELETE FROM computer WHERE id=?;");
			stmt.setLong(1, id);
			//String sql = "DELETE FROM computer WHERE id='"+id+"';";
			stmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(stmt,conn);
		}

	}
	/**
	 * @param stmt
	 * @param conn
	 * @throws SQLException
	 */
	public void close(PreparedStatement stmt, Connection conn) throws SQLException{
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
	}

}
