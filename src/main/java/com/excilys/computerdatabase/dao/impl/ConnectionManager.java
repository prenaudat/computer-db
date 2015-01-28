package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectionManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConnectionManager.class);

	public static void close(Connection connection, PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.warn("Error closing statement");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.warn("Error closing connection");
			}
		}
	}

	public static void close(Connection connection, PreparedStatement stmt,
			ResultSet rs) {
		close(connection, stmt);
		try {
			rs.close();
		} catch (SQLException e) {
			LOGGER.warn("Error closing ResultSet");
		}
	}
}