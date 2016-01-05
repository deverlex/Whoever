package vn.whoever.dao;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

public interface DBAccess {
	
	/**
	 * TODO: interface model for databases connector DBMS
	 * 
	 */
	public void openAccessDatabases();
	public void readDatabases();
	public void writeDatabases();
	
}
