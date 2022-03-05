package models.requests;

import java.io.File;
import java.io.Serializable;

import models.Game;

public class Request_CreateGame extends Request implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game game;

	public Request_CreateGame(Game game) {
		super("createGame");
		this.game = game;
	}
	
	public Request_CreateGame() {
		super("createGame");
		this.game = new Game();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Request_CreateGame))
			return false;
		Request_CreateGame other = (Request_CreateGame) obj;
	
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request_CreateGame [game=" + game + "]";
	}	
	
}