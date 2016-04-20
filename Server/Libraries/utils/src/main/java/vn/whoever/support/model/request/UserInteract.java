package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Interacts;

@XmlRootElement(name = "interactStatus")
@XmlType(propOrder = {"interact"})
@JsonPropertyOrder(value = {"interact"})
public class UserInteract implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 188477922874593L;
	
	@XmlElement(name = "interact", required = true)
	private Interacts interact;

	public UserInteract() {
		super();
	}

	public UserInteract(Interacts interact) {
		super();
		this.interact = interact;
	}

	public Interacts getInteract() {
		return interact;
	}

	public void setInteract(Interacts interact) {
		this.interact = interact;
	}
	
}
