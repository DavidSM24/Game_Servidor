package models.responses;

import java.io.Serializable;

import models.User;

public class Response_Edit extends Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean accepted;
	private User user;

	public Response_Edit(User user,boolean accepted) {
		super("edit");
		this.accepted=accepted;
		this.user = user;
	}

	public Response_Edit() {
		super("edit");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "Response_Edit [accepted=" + accepted + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (accepted ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Response_Edit))
			return false;
		Response_Edit other = (Response_Edit) obj;
		if (accepted != other.accepted)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
}
