package vn.whoever.support.model.utils;

public enum States {

	ACTIVE("active"),
	INACTIVE("inactive"),
	DELETED("deleted"),
	LOCKED("locked");
	
	private String state;
	
	States(final String state) {
		this.state = state;
	}
	
	public String getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return this.state;
	}
	
	public String getName() {
		return this.getName();
	}
}
