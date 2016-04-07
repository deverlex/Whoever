package vn.whoever.support.model.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "location")
@XmlType(propOrder = {"xLoc", "yLoc"})
@JsonPropertyOrder(value = {"xLoc", "yLoc"})
public class Location {
	
	@XmlElement(name = "xLoc", required = true)
	private Double xLoc;
	
	@XmlElement(name = "yLoc", required = true)
	private Double yLoc;
	
	public Location() {}
	
	public Location(Double xLoc, Double yLoc) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}

	public Double getxLoc() {
		return xLoc;
	}

	public void setxLoc(Double xLoc) {
		this.xLoc = xLoc;
	}

	public Double getyLoc() {
		return yLoc;
	}

	public void setyLoc(Double yLoc) {
		this.yLoc = yLoc;
	}
	
}
