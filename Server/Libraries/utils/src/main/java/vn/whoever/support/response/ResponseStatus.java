package vn.whoever.support.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseStatus {

	private String idStatus;
	private String idPoster;
	private String namePoster;
	private String timePost;
	private String content;
	private int totalLike;
	private int totalDislike;
	
	private List<ResponseComment> comments = new ArrayList<ResponseComment>();

	public ResponseStatus() {
		super();
	}

	public ResponseStatus(String idStatus, String idPoster, String namePoster, String timePost, String content,
			int totalLike, int totalDislike, List<ResponseComment> comments) {
		super();
		this.idStatus = idStatus;
		this.idPoster = idPoster;
		this.namePoster = namePoster;
		this.timePost = timePost;
		this.content = content;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.comments = comments;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getIdPoster() {
		return idPoster;
	}

	public void setIdPoster(String idPoster) {
		this.idPoster = idPoster;
	}

	public String getNamePoster() {
		return namePoster;
	}

	public void setNamePoster(String namePoster) {
		this.namePoster = namePoster;
	}

	public String getTimePost() {
		return timePost;
	}

	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}

	public int getTotalDislike() {
		return totalDislike;
	}

	public void setTotalDislike(int totalDislike) {
		this.totalDislike = totalDislike;
	}

	public List<ResponseComment> getComments() {
		return comments;
	}

	public void setComments(List<ResponseComment> comments) {
		this.comments = comments;
	}
}
