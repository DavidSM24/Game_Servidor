package models;

import models.Request;

public class Request_Login extends Request{
	
	String username;
	String password;
	
	public Request_Login(String username, String password) {
		super("login");
		this.username = username;
		this.password = password;
	}
	
	public Request_Login() {
		super("login");
		this.username = "";
		this.password = "";
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Request_Login))
			return false;
		Request_Login other = (Request_Login) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request_Login [username=" + username + ", password=" + password + "]";
	}
}
