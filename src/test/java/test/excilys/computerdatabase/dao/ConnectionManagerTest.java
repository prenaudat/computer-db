package test.excilys.computerdatabase.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.excilys.computerdatabase.cli.Client;
import com.excilys.computerdatabase.exception.PersistenceException;
/**
 * @author excilys
 *Singleton Connection Manager to give and close connections.
 */
public enum ConnectionManagerTest {
	INSTANCE;
	/**
	 *JDBC Driver Class name 
	 */
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * Database URL 
	 */
	private static final String DB_URL = "jdbc:mysql://localhost/computer-database-db-test?zeroDateTimeBehavior=convertToNull";
	/**
	 *Database id 
	 */
	private static final String USER = "admincdb-test";
	/**
	 * Database Password
	 */
	private static final String PASS = "qwerty1234";

	/**
	 * Load JDBC driver and create new ConnectionManager
	 */
	private ConnectionManagerTest(){
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
	public static ConnectionManagerTest getInstance() {
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
			throw new PersistenceException("Failed to load " + JDBC_DRIVER);
		}
	}
}
