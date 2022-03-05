package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean admin;
	private String username;
	private String password;
	private double money;
	private List<Game> games;
		
	public User() {
		this.games=new ArrayList<Game>();
	}

	public User(int id, boolean admin, String username, String password, double money, List<Game> games) {
		super();
		this.id = id;
		this.admin = admin;
		this.username = username;
		this.password = password;
		this.money = money;
		this.games = games;
	}

	public User(boolean admin, String username, String password, double money, List<Game> games) {
		super();
		this.admin = admin;
		this.username = username;
		this.password = password;
		this.money = money;
		this.games = games;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", admin=" + admin + ", username=" + username + ", password=" + password + ", money="
				+ money + ", nº games=" + games.size() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
