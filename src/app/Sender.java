package app;

import java.io.Serializable;

public class Sender implements Serializable {

	private String username;
	
	public Sender(String name) {
		this.username = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
