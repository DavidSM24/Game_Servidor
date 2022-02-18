package tests;

import java.util.ArrayList;

import DAOs.UserDAO;
import models.Game;
import models.User;

public class UserTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u=new User(false, "prueba", "prueba", 0, new ArrayList<Game>());
		UserDAO.insert(u);
	}

}
