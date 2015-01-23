package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * @author excilys Singleton Connection Manager to give and close connections.
 */
public enum ConnectionManager {
	INSTANCE;
	BoneCP connectionPool ;

	/**
	 * JDBC Driver Class name
	 */
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * Database URL
	 */
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	/**
	 * Database id
	 */
	private static final String USER = "admincdb";
	/**
	 * Database Password
	 */
	private static final String PASS = "qwerty1234";

	// static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 * Load JDBC driver and create new ConnectionManager
	 */
	private ConnectionManager() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException("Failed to load " + JDBC_DRIVER);
		}
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(DB_URL);
		config.setUser(USER);
		config.setPassword(PASS);
		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(1);
		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			throw new PersistenceException("Failed to load Configuration" + config);
		}
	}

	/**
	 * Access instance of ConnectionManager
	 *
	 * @return Instance of ConnectionManager
	 */
	public static ConnectionManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Get a connection
	 * 
	 * @return Connection connection
	 */
	public Connection getConnection() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			throw new PersistenceException("Couldn't get connection");
		} 
	}

	/**
	 * Close a Connection and a PreparedStatement
	 * 
	 * @param stmt
	 *            Statement to be executed on connection
	 * @param conn
	 *            Connection conn
	 */
	public void close(PreparedStatement stmt, Connection conn) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// logger.warn("Can't close connection");
			throw new PersistenceException("Failed to load " + JDBC_DRIVER);
		}
	}

	/**
	 * Close PreparedStatement, Connection and ResultSet
	 * 
	 * @param stmt
	 *            Statement to be closed
	 * @param conn
	 *            Connexion to be closed
	 * @param rs
	 *            ResultSet to be closed
	 */
	public void close(PreparedStatement stmt, Connection conn, ResultSet rs) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// logger.warn("Can't close connection");
			throw new PersistenceException("Failed to load " + JDBC_DRIVER);
		}
	}
}
