package vn.whoever.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;

import org.json.JSONException;
import org.json.JSONObject;

import vn.whoever.dao.UserDAO;
import vn.whoever.models.User;
import vn.whoever.models.Verify;
import vn.whoever.utils.JSONStream;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */
@Path("/users")
public class UserService implements Service {

	/**
	 * <h>Example: /SignIn/nguyendo94vn@gmail.com;password=123456</h>
	 * QueryParam /SignIn?email=nguyendo94vn@gmail.com&pasword=123456&password=123456
	 * if passowrd is QueryParam with List<?>
	 * @param email
	 * @param password
	 * @return
	 */
	
	UserDAO userDAO = new UserDAO();
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserInfor(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		
		/**
		 * TODO: package with JSON before send to request
		 */
		
		return "";
	}
	
	@POST
	@Path("/login-anonymous")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Verify loginUseAnonymous(InputStream inputStream) {
		
		JSONStream jsonStream = new JSONStream(inputStream);
		JSONObject jsonObject = jsonStream.getJSONObject();
		try {
			System.out.println("imei: " + jsonObject.getString("imei"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("return to client result");
		return new Verify(true);
	}
	
	@POST
	@Path("/create-account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getCreateNewAccount(InputStream inputStream) {
		JSONStream jsonStream = new JSONStream(inputStream);
		JSONObject jsonObject = jsonStream.getJSONObject();
		
		
		return null;
	}
	
	
	
	@GET
	@Path("/forget-password/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean forgetPassword(@PathParam("email") String email) {
		
		return true;
	}
	
	@POST
	@Path("/update-password/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePassword(@PathParam("email") String email,
			@MatrixParam("password") String password,
			@MatrixParam("rePassword") String rePassword) {
		
	}
	
	@POST
	@Path("/update-email/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmail(@PathParam("email") String email,
			@MatrixParam("newEmail") String newEmail) {
		
	}
	
	@Override
	public String getSupportOparations() {
		return null;
	}

}
