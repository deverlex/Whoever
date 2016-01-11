package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	private int id;
	private String content;
	private Date timeRead;
	private User receiver;
	private String image;
	private boolean isReaded;
	
	/**
	 * image as String type Base64
	 * 
	 */
	
	private ArrayList<Message> messages;
	
	public Message() {
		messages = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getTimeRead() {
		return timeRead;
	}
	
	@XmlElement
	public void setTimeRead(Date timeRead) {
		this.timeRead = timeRead;
	}
	
	public User getReceiver() {
		return receiver;
	}
	
	@XmlElement
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public String getImage() {
		return image;
	}
	
	@XmlElement
	public void setImage(String image) {
		this.image = image;
	}
	
	public boolean isReaded() {
		return isReaded;
	}
	
	@XmlElement
	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	@XmlElement
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

}
