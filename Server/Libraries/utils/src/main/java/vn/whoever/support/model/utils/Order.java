package vn.whoever.support.model.utils;

/**
 * @author Nguyen Van Do
 * 
 * Define getting for get status mode - get by friends or friends & nearby
 */

public enum Order {
	friends("friends"), nearby("nearby");

	private String order;

	private Order(final String order) {
		this.order = order;
	}

	public String getOrder() {
		return this.order;
	}

	@Override
	public String toString() {
		return this.order;
	}

	public String getName() {
		return this.getName();
	}
}
