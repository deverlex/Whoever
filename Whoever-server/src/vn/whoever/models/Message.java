package vn.whoever.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	private int id;
	private String content;
	private Date timeSend;
	private User sender;
	private User receiver;
	
	private String image;
	
	/**
	 * image as String type Base64
	 * 
	 */

	
}
