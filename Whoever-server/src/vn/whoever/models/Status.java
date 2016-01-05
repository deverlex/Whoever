package vn.whoever.models;

import java.sql.Date;
import java.util.ArrayList;

public class Status {
	
	private int id;
	private String content;
	private String writer;
	private Date dateWriter;
	private byte[] image;
	
	private ArrayList<Reply> arrComment;
	
}
