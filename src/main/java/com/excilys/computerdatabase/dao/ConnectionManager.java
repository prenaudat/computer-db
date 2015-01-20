package com.excilys.computerdatabase.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.excilys.computerdatabase.exception.PersistenceException;
/**
 * @author excilys
 *Singleton Connection Manager to give and close connections.
 */
public enum ConnectionManager {
	INSTANCE;
	/**
	 *JDBC Driver Class name 
	 */
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * Database URL 
	 */
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	/**
	 *Database id 
	 */
	private static final String USER = "admincdb";
	/**
	 * Database Password
	 */
	private static final String PASS = "qwerty1234";
//	static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 * Load JDBC driver and create new ConnectionManager
	 */
	private ConnectionManager(){
		try{
		Class.forName(JDBC_DRIVER);
		} catch(ClassNotFoundException e){
			throw new PersistenceException("Failed to load " + JDBC_DRIVER);
		}
	}
	/**
	 * Access instance of ConnectionManager
	 *@return Instance of ConnectionManager
	 */
	public static ConnectionManager getInstance() {
		return 	INSTANCE;
	}
	/**
	 * Get a connection
	 * @return Connection connection
	 */
	public Connection getConnection() {
		try{
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch(SQLException e){
//			logger.warn("Failed to connect to database");
			throw new PersistenceException("Failed to connect to database");
		}
	}
	/**
	 * Close a connection
	 * @param stmt Statement to be executed on connection
	 * @param conn Connection conn
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
//			logger.warn("Can't close connection");
			throw new PersistenceException("Failed to load " + JDBC_DRIVER);
		}
	}
}
