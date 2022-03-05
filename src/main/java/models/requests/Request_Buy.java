package models.requests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Game;
import models.User;

public class Request_Buy extends Request implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Game> games;
	User user;
	public Request_Buy( List<Game> game, User user) {
		super("request");
		this.games = game;
		this.user = user;
	}
	public Request_Buy() {
		super("request");
		this.games = new ArrayList<Game>();
		this.user = new User();
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGame(List<Game> game) {
		this.games = game;
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
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request_Buy other = (Request_Buy) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
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
		return "Request_Buy [game=" + games + ", user=" + user + "]";
	}
	
	
	
}
