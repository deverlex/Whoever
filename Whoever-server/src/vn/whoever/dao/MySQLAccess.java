package vn.whoever.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess implements DBAccess {
		
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	
	public static void main(String[] args) {
		MySQLAccess access = new MySQLAccess();
		access.openAccessDatabases();
		access.writeDB(access.readDatabases("select * from users"));
	}
	
	public void writeDB(ResultSet resultSet) {
		try{
			System.out.println("Print data in Database!");
			while(resultSet.next()) {
				System.out.println("id: " + resultSet.getInt("id"));
				System.out.println("Name: " + resultSet.getString("nickName"));
				System.out.println("Email: " + resultSet.getString("email"));
				System.out.println("Password: " + resultSet.getString("password"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void openAccessDatabases() {
		// TODO Auto-generated method stub
		/**
		 * TODO: load config from file
		 */
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/whoeverdb?useSSL=false&"
					+ "user=root&password=1903");
			
			statement = connection.createStatement();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
		
	}

	@Override
	public ResultSet readDatabases(String strQuery) {
		// TODO Auto-generated method stub
		if(statement != null) {
			try {
				return statement.executeQuery(strQuery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				close();
			}
		}
		return null;
	}

	@Override
	public void writeDatabases() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void close() {
		try {			
			if(statement != null) {
				statement.close();
			}
			
			if(connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			statement = null;
			connection = null;
		}
	}
}
