package vn.whoever.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.whoever.models.User;

/**
 * 
 * @author spider man
 * TODO: data access object
 */
public class LoginDAO {

	private User user = null;
	
	public User getUser(String email, String password) {
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
			System.out.println(user.getId());
			System.out.println(user.getEmail());
			System.out.println(user.getNickName());
			System.out.println(user.getPassword());
			return user;
		} else {
			return null;
		}
	}
}
