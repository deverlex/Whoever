package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Interacts;

@XmlRootElement(name = "interactStatus")
@XmlType(propOrder = {"ssoId", "interact"})
@JsonPropertyOrder(value = {"ssoId", "interact"})
public class InteractStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 188477922874593L;
	
	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "interact", required = true)
	private Interacts interact;

	public InteractStatus() {
		super();
	}

	public InteractStatus(String ssoId, Interacts interact) {
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
