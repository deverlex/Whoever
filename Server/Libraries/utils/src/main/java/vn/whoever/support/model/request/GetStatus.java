package vn.whoever.support.model.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Order;

@XmlRootElement(name = "GetStatus")
@XmlType(propOrder = {"ssoId", "order", "lastLevel", "location"})
@JsonPropertyOrder(value = {"ssoId", "order", "lastLevel", "location"})
public class GetStatus {
	
	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "order")
	private Order order;
	
	@XmlElement(name = "lastLevel", required = true)
	private Integer lastLevel;
	
	@XmlElement(name = "location", required = true)
	private Location location;
		
	public GetStatus() {
		super();
	}

	public GetStatus(String ssoId, Order order, Integer lastLevel, Location location) {
		super();
		this.ssoId = ssoId;
		this.order = order;
		this.lastLevel = lastLevel;
		this.location = location;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getLastLevel() {
		return lastLevel;
	}

	public void setLastLevel(Integer lastLevel) {
		this.lastLevel = lastLevel;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
