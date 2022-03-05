package models.responses;

import java.io.Serializable;

import models.Game;

public class Response_CreateGame extends Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean accepted;
	private Game game;
	public Response_CreateGame() {
		super("createGame");
	}
	public Response_CreateGame(boolean accepted, Game game) {
		super("createGame");
		this.accepted = accepted;
		this.game = game;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response_CreateGame other = (Response_CreateGame) obj;
		if (accepted != other.accepted)
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Response_CreateGame [accepted=" + accepted + ", game=" + game + "]";
	}
	
	
	
}
