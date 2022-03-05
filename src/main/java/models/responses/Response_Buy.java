package models.responses;

import java.io.Serializable;

import models.User;

public class Response_Buy extends Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean accepted;
	private User user;
	
	public Response_Buy() {
		super("buy");
	}

	public Response_Buy(boolean accepted, User user) {
		super("buy");
		this.accepted = accepted;
		this.user = user;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response_Buy other = (Response_Buy) obj;
		if (accepted != other.accepted)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Response_Buy [accepted=" + accepted + ", user=" + user + "]";
	}
	
	
	
}