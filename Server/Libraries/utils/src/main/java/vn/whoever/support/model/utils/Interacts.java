package vn.whoever.support.model.utils;

public enum Interacts {

	like("like"),
	dislike("dislike"),
	normal("normal");
	
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
