package vn.whoever.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/UpdateUserService")
public class UpdateUserService {

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
	
	
}
