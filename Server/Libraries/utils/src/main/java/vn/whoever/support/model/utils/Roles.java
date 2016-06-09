package vn.whoever.support.model.utils;

/**
 * @author Nguyen Van Do
 *	Contain define role of account user
 */
public enum Roles {

	USER("USER"), ADMIN("ADMIN"), MODERATOR("MODERATOR"), EDITOR("EDITOR"), DBA("DBA");

	private String role;

	private Roles(final String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}

	@Override
	public String toString() {
		return this.role;
	}

	public String getName() {
		return this.getName();
	}
}
