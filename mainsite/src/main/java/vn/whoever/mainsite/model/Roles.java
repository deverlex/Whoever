package vn.whoever.mainsite.model;

public enum Roles {

	ANONYMOUS("anonymous"),
	USER("user"),
	ADMIN("admin"),
	MODERATOR("moderator"),
	EDITOR("editor"),
	DBA("dba");
	
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
