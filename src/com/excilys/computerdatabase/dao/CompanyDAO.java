package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Scanner; 
import com.excilys.computerdatabase.model.Company;

public enum CompanyDAO {
	INSTANCE;
	public static CompanyDAO getInstance() {
		return INSTANCE;
	}

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASS = "qwerty1234";
	private static Map<Integer, Integer> cache = new ConcurrentHashMap<>();

	public Company getCompanyById(long id) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			String sql = "select * from company where id =" + id + ";";
			ResultSet rs = stmt.executeQuery(sql);
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
			String sql = "select COUNT(id) as rowcount from company;";
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
	public ArrayList<Company> getCompanyList(int currentIndex, int pageSize) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			final String sql = "select * from company LIMIT "+currentIndex+" , "+pageSize+";";
			final ResultSet rs = stmt.executeQuery(sql);
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
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}

	}
	
	public void createCompany(String name) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			final String sql = "INSERT INTO company(name) VALUES ("+name+");";
			stmt.execute(sql);

	}catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
}}
	
	public void updateCompany(long id,String name) throws SQLException{
		Connection conn=null;
		Statement stmt=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			stmt = conn.createStatement();
			final String sql = "UPDATE company SET name='"+name+"' WHERE id="+id+" ;";
			stmt.execute(sql);

	}catch (ClassNotFoundException | SQLException e) {
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
}