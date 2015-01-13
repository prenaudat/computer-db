package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Scanner; 

import com.excilys.computerdatabase.model.Company;

/**
 * @author paulr_000
 *
 */
public enum CompanyDAO {
	INSTANCE;
	/**
	 * @return
	 */
	public static CompanyDAO getInstance() {
		return INSTANCE;
	}

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASS = "qwerty1234";
	private static Map<Integer, Integer> cache = new ConcurrentHashMap<>();

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Company getCompanyById(long id) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("select * from company where id =?");
			stmt.setLong(1, id);
			//String sql = "select * from company where id =" + id + ";";
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Company(rs.getLong("id"), rs.getString("name"));
			} else {
				return null;// Display values
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
			stmt = conn.prepareStatement("select COUNT(id) as rowcount from company;");
			ResultSet rs = 	stmt.executeQuery();
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
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Company> getCompanyList(int currentIndex, int pageSize) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("select * from company LIMIT ?,?;");
			stmt.setInt(1, currentIndex);
			stmt.setInt(2, pageSize);
			final ResultSet rs = stmt.executeQuery();
			ArrayList<Company> companyList = new ArrayList<Company>();
			while (rs.next()) {
				companyList.add(new Company(rs.getLong("id"), rs
						.getString("name")));
			}
			return companyList;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			close(stmt,conn);
		}

	}
	
	/**
	 * @param name
	 * @throws SQLException
	 */
	public void createCompany(String name) throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("INSERT INTO company(name) VALUES (?);");
			stmt.setString(1, name);
			stmt.executeUpdate();

	}catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		close(stmt,conn);

}}
	
	/**
	 * @param id
	 * @param name
	 * @throws SQLException
	 */
	public void updateCompany(long id,String name) throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.prepareStatement("UPDATE company SET name=? WHERE id=? ;");
			stmt.setString(1, name);
			stmt.setLong(2,id);
			stmt.executeUpdate();

	}catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		close(stmt,conn);
}
}
	public void close(PreparedStatement stmt, Connection conn) throws SQLException{
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
	}
	
}