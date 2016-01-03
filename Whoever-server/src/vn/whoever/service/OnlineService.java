package vn.whoever.service;

import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Path;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

@Path("/Online")
public class OnlineService {
	
	private ConcurrentHashMap<String, String> userOnline = new ConcurrentHashMap<String, String>();
	
	
}
