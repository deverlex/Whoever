package vn.whoever.support.model.utils;

/**
 * @author Nguyen Van Do
 * 
 * Define interacting on status, comment
 */
public enum Interacts {

	like("like"), // 1
	dislike("dislike"), // -1
	normal("normal"); // 0

	private String interact;

	private Interacts(String interact) {
		this.interact = interact;
	}

	public String getInteract() {
		return this.interact;
	}

	@Override
	public String toString() {
		return this.interact;
	}

	public String getName() {
		return this.getName();
	}
}
