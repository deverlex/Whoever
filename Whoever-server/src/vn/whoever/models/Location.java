package vn.whoever.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.whoever.utils.Point;

@XmlRootElement
public class Location {

	private String nameCountry;
	private Point point; //point of user online
	
	public Location() {}
	
	public Location(String nameCountry, Point point) {
		this.nameCountry = nameCountry;
		this.point = point;
	}

	public String getNameCountry() {
		return nameCountry;
	}

	@XmlElement
	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}

	public Point getPoint() {
		return point;
	}

	@XmlElement
	public void setPoint(Point point) {
		this.point = point;
	}
}
