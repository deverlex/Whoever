package vn.whoever.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;

import vn.whoever.dao.UserDAO;
import vn.whoever.models.User;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */
@Path("/UserService")
public class UserService implements Service {

	/**
	 * <h>Example: /SignIn/nguyendo94vn@gmail.com;password=123456</h>
	 * QueryParam /SignIn?email=nguyendo94vn@gmail.com&pasword=123456&password=123456
	 * if passowrd is QueryParam with List<?>
	 * @param email
	 * @param password
	 * @return
	 */
	
	@GET
	@Path("/Login")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInfor(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		UserDAO loginDAO = new UserDAO();
		return loginDAO.getUser(email, password);
	}
	
	@GET
	@Path("/ForgetPassword/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean forgetPassword(@PathParam("email") String email) {
		
		return true;
	}
	
	@POST
	@Path("/UpdatePassword/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePassword(@PathParam("email") String email,
			@MatrixParam("password") String password,
			@MatrixParam("rePassword") String rePassword) {
		
	}
	
	@POST
	@Path("/UpdateEmail/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmail(@PathParam("email") String email,
			@MatrixParam("newEmail") String newEmail) {
		
	}
	
	@Override
	public String getSupportOparations() {
		return null;
	}

}
