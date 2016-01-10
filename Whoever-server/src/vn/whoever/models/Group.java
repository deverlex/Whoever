package vn.whoever.models;

import java.util.HashMap;

public class Group {

	private HashMap<String, User> members = null;

	public Group() {
		members = new HashMap<>();
	}

	public Group(HashMap<String, User> members) {
		this.members = members;
	}

	public void putMember(String idMember, User member) {
		if (this.members != null) {
			this.members.put(idMember, member);
		}
	}

	public User getMember(String idMember) {
		if (this.members != null) {
			return this.members.get(idMember);
		} else {
			return null;
		}
	}

	public int getCountMember() {
		if (this.members != null) {
			return this.members.size();
		} else {
			return 0;
		}
	}
}
