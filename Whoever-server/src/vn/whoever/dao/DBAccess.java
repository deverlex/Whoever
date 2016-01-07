package vn.whoever.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

public abstract class DBAccess {
	
	/**
	 * TODO: interface model for databases connector DBMS
	 * 
	 */
	
	protected abstract void openAccessDatabases();
	protected abstract ResultSet readDatabases(String strQuery);
	protected abstract void writeDatabases();
	protected abstract void close();
	
}
