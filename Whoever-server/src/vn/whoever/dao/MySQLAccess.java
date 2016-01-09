package vn.whoever.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class MySQLAccess extends DBAccess {
	
	/**
	 * Desc: Singleton class
	 * 
	 */
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	
	private static MySQLAccess sqlAccess = new MySQLAccess();
	
	private MySQLAccess() { }
	
	public static synchronized MySQLAccess getInstance() {
		sqlAccess.openAccessDatabases();
		return sqlAccess;
	}

	@Override
	protected void openAccessDatabases() {
		/**
		 * TODO: load configuration from file
		 */
		if(connection == null && statement == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/whoeverdb?useSSL=false&"
						+ "user=root&password=1903");
				
				statement = connection.createStatement();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
	}

	@Override
	protected synchronized ResultSet readDatabases(String sqlQuery) {
		if(statement != null) {
			try {
				return statement.executeQuery(sqlQuery);
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return null;
	}

	@Override
	protected synchronized boolean writeDatabases(String sqlUpdate) {
		// TODO Auto-generated method stub
		if(statement != null) {
			try {
				statement.executeUpdate(sqlUpdate);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	protected void close() {
		try {			
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			statement = null;
			connection = null;
		}
	}
}
