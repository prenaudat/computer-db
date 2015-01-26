package com.excilys.computerdatabase.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.exception.PersistenceException;
import com.jolbox.bonecp.BoneCP;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * @author excilys Singleton Connection Manager to give and close connections.
 */
public enum ConnectionManager {
	INSTANCE;
	BoneCP connectionPool;
	private ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
	private final Logger LOGGER = LoggerFactory
			.getLogger(ConnectionManager.class);

	/**
	 * Load JDBC driver and create new ConnectionManager
	 */
	private ConnectionManager() {
		InputStream is = ConnectionManager.class.getClassLoader()
				.getResourceAsStream("mysql.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e1) {
			LOGGER.warn("Can't load Connection properties");
		}
		try {
			is.close();
		} catch (IOException e1) {
			LOGGER.warn("Can't close Connection properties");
		}
		final String JDBC_DRIVER = props.getProperty("JDBC_DRIVER");
		final String DB_URL = props.getProperty("DB_URL");
		final String USER = props.getProperty("USER");
		final String PASS = props.getProperty("PASS");

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException("Failed to load JDBC_DRIVER"
					+ JDBC_DRIVER);
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
			throw new PersistenceException("Failed to load Configuration"
					+ config);
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
	 * Get a ThreadLocal Connection from BoneCP connection Pool.
	 * 
	 * @return Connection connection
	 */
	public Connection getConnection() {
		if (connection.get() == null) {
			try {
				connection.set(connectionPool.getConnection());
			} catch (SQLException e) {
				LOGGER.warn("No connection available. Bitch.");
			}
			return connection.get();
		} else {
			return connection.get();
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
			LOGGER.warn("can't close PreparedStatement and Connection");
			throw new PersistenceException("Failed to close connections");
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
			LOGGER.warn("Can't close connection ResultSet or Statement");
			throw new PersistenceException("Failed to close connections");
		}
	}
}
