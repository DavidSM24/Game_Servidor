package tests;

import java.util.ArrayList;

import DAOs.UserDAO;
import models.Game;
import models.User;
import models.requests.Request_Login;

public class UserTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Request_Login rl = new Request_Login("pepito", "1234");
		System.out.println(rl);
	}

}
