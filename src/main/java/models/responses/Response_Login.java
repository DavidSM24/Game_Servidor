package models.responses;

import models.Shop;
import models.User;

public class Response_Login extends Response{

	private boolean accepted;
	private User user;
	private Shop shop;
	
	public Response_Login() {
		super("login");
	}
	public Response_Login(boolean accepted, User user, Shop shop) {
		super("login");
		this.accepted = accepted;
		this.user = user;
		this.shop = shop;
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
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Response_Login))
			return false;
		Response_Login other = (Response_Login) obj;
		if (accepted != other.accepted)
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
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
		return "Response_Login [name="+ super.getName() +", accepted=" + accepted + ", user=" + user + ", shop=" + shop + "]";
	}
	
	
	
}
