package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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

public enum ComputerDAO {
	INSTANCE;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASS = "qwerty1234";
	private Map<Integer, Integer> cache = new ConcurrentHashMap<>();
	private static DateTimeFormatter getTimestampformatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm");
	public static ComputerDAO getInstance(){
		return INSTANCE;
	}

	public Computer getComputerById(long id) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id where cpt.id="
					+ id + ";";
			ResultSet rs = stmt.executeQuery(sql);
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
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
	}

	}

	public ArrayList<Computer> getComputersByCompanyId(final long id) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			final String sql = "select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id where cmp.id="
					+ id + ";";
			final ResultSet rs = stmt.executeQuery(sql);
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
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
	}
	}
	public void updateComputer(long id, String name, Timestamp introduced, Timestamp discontinued, int companyId) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "UPDATE computer SET name='"+name+"', introduced='"+introduced+"', discontinued='"+discontinued+"', company_id="+companyId+" WHERE id="+id+";";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
	}

	}

	public void createComputer(String name, Timestamp introduced, Timestamp discontinued, int companyId) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "Insert into computer(name,introduced, discontinued, company_id) VALUES ('"+name+"', '"+introduced+"','"+discontinued+"', "+companyId+");";
			stmt.execute(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
	}

	}
	public ArrayList<Computer> getComputerList(int currentIndex, int pageSize) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
	    	Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			final String sql = "select * from computer cpt LEFT JOIN company cmp ON cpt.company_id=cmp.id LIMIT "+currentIndex+" , "+pageSize+";";
			final ResultSet rs = stmt.executeQuery(sql);
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
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
	}
	}
	public int count() throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			String sql = "select COUNT(id) as rowcount from computer;";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int count = rs.getInt("rowcount");
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}

	}
	public int count(long id) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			String sql = "select COUNT(id) as rowcount from computer where company_id =" + id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int count = rs.getInt("rowcount");
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}

	}
	public int remove(long id) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			String sql = "DELETE FROM computer WHERE id='"+id+"';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int count = rs.getInt("rowcount");
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}

	}

}
