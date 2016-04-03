package vn.whoever.support.model.utils;

public class Location {
	
	private Double xLoc, yLoc;
	
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
