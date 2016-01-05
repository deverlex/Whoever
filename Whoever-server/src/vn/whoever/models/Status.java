package vn.whoever.models;

import java.sql.Date;
import java.util.ArrayList;

public class Status {
	
	private int idStatus;
	private String contentStatus;
	private String senderStatus;
	private Date dateWriterStatus;
	private byte[] avatarUserStatus;
	private byte[] imageStatus;
	
	private ArrayList<Reply> arrCommentStatus;
	
}
