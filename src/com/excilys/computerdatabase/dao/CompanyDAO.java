package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

	public Company getCompanyById(long id) {
		try {
			Class.forName(JDBC_DRIVER);
			final Connection conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			System.out.println("Creating statement...");
			Statement stmt = conn.createStatement();
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
		}

	}

	public ArrayList<Company> getCompanyList() {
		try {
			Class.forName(JDBC_DRIVER);
			final Connection conn = DriverManager.getConnection(DB_URL, USER,
					PASS);
			System.out.println("Creating statement...");
			final Statement stmt = conn.createStatement();
			final String sql = "select * from company;";
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
		}

	}
}