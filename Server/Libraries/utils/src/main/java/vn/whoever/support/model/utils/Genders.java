package vn.whoever.support.model.utils;

public enum Genders {
	
	male("male"),
	female("female"),
	unknown("unknown");
	
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
