package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Interacts;

@XmlRootElement(name = "interactStatus")
@XmlType(propOrder = {"idUser", "interact"})
@JsonPropertyOrder(value = {"idUser", "interact"})
public class InteractStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 188477922874593L;
	
	@XmlElement(name = "idUser", required = true)
	private String idUser;
	
	@XmlElement(name = "interact", required = true)
	private Interacts interact;

	public InteractStatus() {
		super();
	}

	public InteractStatus(String idUser, Interacts interact) {
		super();
		this.idUser = idUser;
		this.interact = interact;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Interacts getInteract() {
		return interact;
	}

	public void setInteract(Interacts interact) {
		this.interact = interact;
	}
	
}
