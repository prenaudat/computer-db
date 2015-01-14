package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.cli.Client;


/**
 * @author excilys
 *
 *Singleton Connection Manager to give and close connections efficiently.
 */
public enum ConnectionManager {
	// Singleton form
	INSTANCE;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASS = "qwerty1234";
	static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);

	private ConnectionManager(){
		try{
		Class.forName(JDBC_DRIVER);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public static ConnectionManager getInstance() {
		return 	INSTANCE;
	}

	public Connection getConnection() {
		try{
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
