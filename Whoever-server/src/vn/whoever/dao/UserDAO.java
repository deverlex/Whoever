package vn.whoever.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.whoever.models.User;

/**
 * 
 * @author spider man
 * TODO: data access object
 */
public class UserDAO {

	private User user = null;
	
	public synchronized User getUser(String email, String password) {
		user = new User();
		String sqlQuery = "SELECT * FROM users WHERE email = '" 
							+ email + "' and password = '" + password+"'";
		ResultSet set = MySQLAccess.getInstance().readDatabases(sqlQuery);
		if(set != null) {
			try {
				while(set.next()) {
					user.setId(set.getInt("id"));
					user.setEmail(set.getString("email"));
					user.setNickName(set.getString("nickName"));
					user.setPassword(set.getString("password"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return user;
		} else {
			return null;
		}
	}
	
	public synchronized boolean createAnonymousUser(String imei) {
		String sqlUpdate = "INSERT INTO USERS(...) VALUES(...) ";
		//MySQLAccess.getInstance().writeDatabases(sqlUpdate);
		
		return true;
	}
	
	
}
