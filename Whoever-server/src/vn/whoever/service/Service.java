package vn.whoever.service;

import javax.ws.rs.OPTIONS;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

public interface Service {	
	
	@OPTIONS
	public String getSupportOparations();

}
