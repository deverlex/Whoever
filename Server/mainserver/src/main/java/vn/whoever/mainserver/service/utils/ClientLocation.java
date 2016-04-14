package vn.whoever.mainserver.service.utils;

import java.io.Serializable;

public class ClientLocation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 188773399585L;
	
	private String countryName;
	private String postalCode;
	private Double latitude;
	private Double longitude;
	
	public ClientLocation() {
		super();
	}

	public ClientLocation(String countryName, String postalCode, Double latitude, Double longitude) {
		super();
		this.countryName = countryName;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
