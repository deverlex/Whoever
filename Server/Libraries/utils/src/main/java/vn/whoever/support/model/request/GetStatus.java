package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Order;

@XmlRootElement(name = "getStatus")
@XmlType(propOrder = {"ssoId", "order", "offset", "location"})
@JsonPropertyOrder(value = {"ssoId", "order", "offset", "location"})
public class GetStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1546756534145678L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "order")
	private Order order = Order.nearby;
	
	@XmlElement(name = "offset")
	private int offset = 0;
	
	public GetStatus() {
		super();
	}

	public GetStatus(String ssoId, Order order, int offset) {
		super();
		this.ssoId = ssoId;
		this.order = order;
		this.offset = offset;
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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
