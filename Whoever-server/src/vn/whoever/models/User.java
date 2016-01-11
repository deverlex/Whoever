package vn.whoever.models;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author Nguyen Van Do
 * @version 1.0
 */

@XmlRootElement
public class User implements Serializable {

	/**
	 * TODO: co the ket hop XMLRootElement voi 
	 * dong goi thanh chuoi json
	 * 
	 */
	private static final long serialVersionUID = 19837278912L;
	
	private String id; //email or imei of mobile device
	private String nickName;
	private String password; // not return this element
	private String avatar;
	private Date birthDay; // confirm >= 13 year old
	private String idLanguage;
	
	private int rankWorld;
	private int rankLanguage;
	
	private int countLiked; // da like
	private int countIsLiked; // dc like
	private int countDislike;
	private int countIsDislike;
	private int countComment; // so comment
	private int countIsReply; // dc tra loi = so trich dan + so cmt trong stt
	private int countStatus;
	
	private Queue<Status> queueStatus;
	private Queue<Reply> queueReply;
	
	public User() {
		queueReply = new ArrayDeque<>();
		queueStatus = new ArrayDeque<>();
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Queue<Status> getQueueStatus() {
		return queueStatus;
	}

	public void setQueueStatus(Queue<Status> queueStatus) {
		this.queueStatus = queueStatus;
	}

	public Queue<Reply> getQueueReply() {
		return queueReply;
	}

	public void setQueueReply(Queue<Reply> queueReply) {
		this.queueReply = queueReply;
	}

	/**
	 * TODO: when user login and request to server, server response server
	 * a lot of information there.
	 * 
	 */
	
	public String getId() {
		return id;
	}
	
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	@XmlElement
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	@XmlElement
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}
	
	@XmlElement
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public String getIdLanguage() {
		return idLanguage;
	}
	
	@XmlElement
	public void setIdLanguage(String idLanguage) {
		this.idLanguage = idLanguage;
	}
	
	public int getRankWorld() {
		return rankWorld;
	}
	
	@XmlElement
	public void setRankWorld(int rankWorld) {
		this.rankWorld = rankWorld;
	}
	
	public int getRankLanguage() {
		return rankLanguage;
	}
	
	@XmlElement
	public void setRankLanguage(int rankLanguage) {
		this.rankLanguage = rankLanguage;
	}
	
	public int getCountLiked() {
		return countLiked;
	}
	
	@XmlElement
	public void setCountLiked(int countLiked) {
		this.countLiked = countLiked;
	}
	
	public int getCountIsLiked() {
		return countIsLiked;
	}
	
	@XmlElement
	public void setCountIsLiked(int countIsLiked) {
		this.countIsLiked = countIsLiked;
	}
	
	public int getCountDislike() {
		return countDislike;
	}
	
	@XmlElement
	public void setCountDislike(int countDislike) {
		this.countDislike = countDislike;
	}
	
	public int getCountIsDislike() {
		return countIsDislike;
	}
	
	@XmlElement
	public void setCountIsDislike(int countIsDislike) {
		this.countIsDislike = countIsDislike;
	}
	
	public int getCountComment() {
		return countComment;
	}
	
	@XmlElement
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
	
	public int getCountIsReply() {
		return countIsReply;
	}
	
	@XmlElement
	public void setCountIsReply(int countIsReply) {
		this.countIsReply = countIsReply;
	}
	
	public int getCountStatus() {
		return countStatus;
	}
	
	@XmlElement
	public void setCountStatus(int countStatus) {
		this.countStatus = countStatus;
	}
}
