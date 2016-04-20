package vn.whoever.support.model.request;

import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.whoever.support.model.utils.Interacts;

@XmlRootElement(name = "interactComment")
@XmlType(propOrder = {"ssoId", "interact"})
@JsonPropertyOrder(value = {"ssoId", "interact"})
public class InteractComment {
	
	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "interact", required = true)
	private Interacts interact;
	
	public InteractComment() {
		super();
	}

	public InteractComment(String ssoId, Interacts interact) {
		super();
		this.ssoId = ssoId;
		this.interact = interact;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public Interacts getInteract() {
		return interact;
	}

	public void setInteract(Interacts interact) {
		this.interact = interact;
	}
}
