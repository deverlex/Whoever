package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "postComment")
@XmlType(propOrder = {"ssoId", "content", "isUseAccount"})
@JsonPropertyOrder(value = {"ssoId", "content", "isUseAccount"})
public class PostComment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100449020987462234L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "content", required = true)
	private String content;
	
	@XmlElement(name = "isUserAccount", defaultValue = "false")
	private Boolean isUseAccount;
	
	public PostComment() {
		super();
	}

	public PostComment(String ssoId, String content, Boolean isUseAccount) {
		super();
		this.ssoId = ssoId;
		this.content = content;
		this.isUseAccount = isUseAccount;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsUseAccount() {
		return isUseAccount;
	}

	public void setIsUseAccount(Boolean isUseAccount) {
		this.isUseAccount = isUseAccount;
	}
}
