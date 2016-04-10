package vn.whoever.support.model.request;

public class PostComment {
	
	private String content;
	private String date;
	
	public PostComment() {
		super();
	}

	public PostComment(String content, String date) {
		super();
		this.content = content;
		this.date = date;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
