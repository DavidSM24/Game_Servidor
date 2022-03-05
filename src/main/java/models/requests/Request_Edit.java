package models.requests;

import java.io.Serializable;

import models.User;

public class Request_Edit extends Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	User user;

	public Request_Edit( User user) {
		super("edit");
		this.user = user;
	}

	public Request_Edit() {
		super("edit");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Request_Edit))
			return false;
		Request_Edit other = (Request_Edit) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request_Edit [user=" + user + "]";
	}	
}
