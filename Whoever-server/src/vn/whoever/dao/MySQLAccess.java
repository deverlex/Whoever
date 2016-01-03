package vn.whoever.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
		
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public static void main(String[] args) {
		MySQLAccess access = new MySQLAccess();
		access.readDataBase();
	}
	
	public void readDataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/whoeverdb?useSSL=false&"
					+ "user=root&password=1903");
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select * from users");
			
			writeDB(resultSet);
		}catch(ClassNotFoundException e) {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void writeDB(ResultSet resultSet) throws SQLException {
		System.out.println("Print data in Database!");
		while(resultSet.next()) {
			System.out.println("Name: " + resultSet.getString("name"));
			System.out.println("Email: " + resultSet.getString("email"));
		}
	}
	
	public void close() {
		try {
			if(resultSet != null ) {
				resultSet.close();
			}
			
			if(statement != null) {
				statement.close();
			}
			
			if(connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			
		}
	}
}
