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
	
	private static MySQLAccess sqlAccess = null;
	
	private MySQLAccess() { }
	
	public static MySQLAccess getInstance() {
		if(sqlAccess == null) {
			sqlAccess = new MySQLAccess();
		}
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
	protected ResultSet readDatabases(String sqlQuery) {
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
	protected void writeDatabases() {
		// TODO Auto-generated method stub
		
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
