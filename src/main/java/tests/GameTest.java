package tests;

import DAOs.GameDAO;
import models.Game;
import models.User;

public class GameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setId(1);
		System.err.println(GameDAO.getByUser(u));
	}

}
