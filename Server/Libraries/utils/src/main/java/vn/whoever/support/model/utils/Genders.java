package vn.whoever.support.model.utils;

public enum Genders {
	
	MALE("male"),
	FEMALE("female"),
	UNKNOWN("unknown");
	
	private String sex;
	
	private Genders(String sex) {
		this.sex = sex;
	}
	
	public String getGender() {
		return this.sex;
	}
	
	public String getName() {
		return this.getName();
	}
}
