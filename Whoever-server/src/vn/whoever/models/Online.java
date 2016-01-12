package vn.whoever.models;

import java.util.ArrayList;

public class Online {

	private String idUser;
	private String avatar;
	private boolean isOnline;
	
	private ArrayList<Online> onlines;
	
	public Online() {
		onlines = new ArrayList<>();
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public ArrayList<Online> getOnlines() {
		return onlines;
	}

	public void setOnlines(ArrayList<Online> onlines) {
		this.onlines = onlines;
	}
	
}
