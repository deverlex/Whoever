package vn.whoever.service;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vn.whoever.models.User;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */
@Path("/SignIn")
public class SignInService implements Service {

	/**
	 * <h>Example: /SignIn/nguyendo94vn@gmail.com;password=123456</h>
	 * QueryParam /SignIn?email=nguyendo94vn@gmail.com&pasword=123456&password=123456
	 * if passowrd is QueryParam with List<?>
	 * @param email
	 * @param password
	 * @return
	 */
	
	@GET
	@Path("{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSignInInfor(@PathParam("email") String email,
			@MatrixParam("password") String password) {
		User user = new User();
		return user;
	}
	
	@Override
	public String getSupportOparations() {
		// TODO Auto-generated method stub
		return null;
	}

}
