package vn.whoever.support.model.utils;

public enum Interacts {

	LIKE("like"),
	DISLIKE("dislike");
	
	private String interact;
	
	private Interacts(String interact) {
		this.interact = interact;
	}
	
	public String getInteract() {
		return this.interact;
	}
	
	public String getName() {
		return this.getName();
	}
}