package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Order;

@XmlRootElement(name = "getStatus")

public class GetStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1546756534145678L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "order")
	private Order order = Order.nearby;
	
	@XmlElement(name = "lastLevel")
	private int lastLevel;
	
	@XmlElement(name = "location")
	private Location location;
	
	public GetStatus() {
		super();
	}

	public GetStatus(String ssoId, Order order, int lastLevel, Location location) {
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

	public int getLastLevel() {
		return lastLevel;
	}

	public void setLastLevel(int lastLevel) {
		this.lastLevel = lastLevel;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
