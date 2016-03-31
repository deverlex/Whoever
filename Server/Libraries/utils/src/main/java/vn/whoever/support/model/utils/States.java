package vn.whoever.support.model.utils;

public enum States {

	active("active"),
	inactive("inactive"),
	locked("locked");
	
	private String state;
	
	private States(final String state) {
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
