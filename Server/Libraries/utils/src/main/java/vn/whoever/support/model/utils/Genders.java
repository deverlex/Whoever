package vn.whoever.support.model.utils;

/**
 * @author Nguyen Van Do
 * 
 * Define gender type of user
 */
public enum Genders {

	male("male"), female("female"), unknown("unknown");

	private String sex;

	private Genders(String sex) {
		this.sex = sex;
	}

	public String getGender() {
		return this.sex;
	}

	@Override
	public String toString() {
		return this.sex;
	}

	public String getName() {
		return this.getName();
	}
}
