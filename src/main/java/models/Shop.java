package models;

import java.util.ArrayList;
import java.util.List;

public class Shop {

	private int id;
	private List<Game> games;
	private double money;
	
	public Shop() {
		this(0,new ArrayList<Game>(),0.0);
	}

	public Shop(List<Game> games, double money) {
		super();
		this.games = games;
		this.money = money;
	}	
	
	public Shop(int id, List<Game> games, double money) {
		super();
		this.id = id;
		this.games = games;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Shop))
			return false;
		Shop other = (Shop) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", games=" + games + ", money=" + money + "]";
	}
	
	
}