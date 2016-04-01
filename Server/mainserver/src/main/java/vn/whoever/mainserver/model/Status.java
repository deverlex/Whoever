package vn.whoever.mainserver.model;

import java.util.Date;

import vn.whoever.support.model.utils.Privacies;

public class Status {
	
	private String idStatus;
	private Users users;
	private String content;
	private Date timePost;
	private Double xLoc;
	private Double yLoc;
	private Privacies privacies = Privacies.normal;
	private Boolean isUseAccount;
}
