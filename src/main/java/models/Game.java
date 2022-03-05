package models;

import java.io.Serializable;

public class Game implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String photo;
	private double price;
	public Game() {
		super();
	}
		
	public Game(String name, String photo, double price) {
		super();
		this.name = name;
		this.photo = photo;
		this.price = price;
	}

	public Game(int id, String name, String photo, double price) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		if (!(obj instanceof Game))
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", photo=" + photo + ", price=" + price + "]";
	}
}
