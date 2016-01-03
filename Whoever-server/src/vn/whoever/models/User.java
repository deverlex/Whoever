package vn.whoever.models;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nickName;
	private String email;
	private String password;
	
	private float index;
	private int totalComment;
	private int totalLike;
	private int totalDislike;
	
	

}
