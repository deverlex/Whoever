package vn.whoever.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Point {
	
	private double x;
	private double y;
	
	public Point() {}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	@XmlElement
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	@XmlElement
	public void setY(double y) {
		this.y = y;
	}
	
}