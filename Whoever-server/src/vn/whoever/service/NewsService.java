package vn.whoever.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

@Path("/news")
public class NewsService {

	@GET
	@Path("/get-news")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNews() {
		/**
		 * TODO: return list status not list user cmt, like, dislike
		 * and not have comment
		 */
		
		return "";
	}
	
	@GET
	@Path("/get-new")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNew() {
		
		return "";
	}
}
