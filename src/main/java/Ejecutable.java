import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.PlatformLoggingMXBean;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DAOs.GameDAO;
import DAOs.ShopDAO;
import DAOs.UserDAO;
import models.Game;
import models.User;
import models.requests.Request;
import models.requests.Request_Buy;
import models.requests.Request_CreateGame;
import models.requests.Request_Login;
import models.requests.Request_Register;
import models.responses.Response_Buy;
import models.responses.Response_CreateGame;
import models.responses.Response_Login;

public class Ejecutable implements Runnable {
	
	public static ServerSocket servidor;

	public static void main(String[] args) {
			
		try {
			servidor=new ServerSocket(6666);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ejecutable ej = new Ejecutable();
		ej.run();
	}

	public void run() {
		// TODO Auto-generated method stub

		Socket client = null;

		try {
			client = servidor.accept();
			System.out.println("hola");
			
			//Runnable runnable=new Ejecutable();
			//runnable.run();
			
			System.out.println("despues del run");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {

			while (true) {
				
				System.out.println("adios");
				
				ObjectInputStream flujoentrada = new ObjectInputStream(client.getInputStream());
				boolean accepted;
				
				System.out.println("me paso de flujoentrad?");
				
				Request request = (Request) flujoentrada.readObject();
			
				System.out.println(request.toString()+"esto es requeestº");
				// switch

				ObjectOutputStream flujosalida;
				Response_Login rpl;

				switch (request.getName()) {
				case "login":

					
					
					accepted=false;
					
					Request_Login rl = (Request_Login) request;
					
					System.out.println(rl);
					
					User u = UserDAO.getByUsername(rl.getUsername());
					
					System.out.println(u);
					
					boolean correct = false;

					if (u != null) {
						if (rl.getPassword().equals(u.getPassword())) {
							correct = true;
						}
					}

					if (correct) {
						rpl = new Response_Login(true, u, ShopDAO.getShop());
					} else {
						rpl = new Response_Login(false, null, null);
					}

					System.out.println(rpl);
					
					flujosalida = new ObjectOutputStream(client.getOutputStream());
					flujosalida.writeObject(rpl);
					break;

				case "register":

					accepted=false;
					
					User newUser = null;
					accepted = false;
					Response_Login rlr;

					Request_Register rr = (Request_Register) request;

					if (!rr.getUsername().equals("") && !rr.getPassword().equals("")) {

						newUser = new User(false, rr.getUsername(), rr.getPassword(), rr.getMoney(),
								new ArrayList<Game>());
						UserDAO.insert(newUser);

						accepted = true;
					}

					if (accepted) {
						rpl = new Response_Login(true, newUser, ShopDAO.getShop());
					} else {
						rpl = new Response_Login(false, null, null);
					}

					flujosalida = new ObjectOutputStream(client.getOutputStream());
					flujosalida.writeObject(rpl);
					
					break;
					
				case "createGame":
					
					accepted=false;
					
					Request_CreateGame rcg=(Request_CreateGame) request;
					Game g=null;
					if(rcg!=null&&rcg.getGame()!=null) {
						g=rcg.getGame();
						
						if(g.getName()!=null
							&&g.getPhoto()!=null
							&&g.getPrice()>0.0) {
							GameDAO.insert(g);
							
							accepted=true;
						}
					}
					
					if(!accepted) {
						g=null;
					}
					
					Response_CreateGame rpcg=new Response_CreateGame(accepted, g);
					flujosalida = new ObjectOutputStream(client.getOutputStream());
					flujosalida.writeObject(rpcg);
					
					break;
					
				case "buy":
					
					accepted=false;
					
					Request_Buy rb=(Request_Buy) request;
					
					if(rb.getUser()!=null&&rb.getGames()!=null&&rb.getUser().getMoney()>=rb.getGames().get(0).getPrice()) {
						UserDAO.buyGame(rb.getUser(), rb.getGames().get(0));
						
						rb.getUser().getGames().add(rb.getGames().get(0));
						
						accepted=true;
					}
					
					Response_Buy rpb=new Response_Buy(accepted,rb.getUser());
					flujosalida = new ObjectOutputStream(client.getOutputStream());
					flujosalida.writeObject(rpb);
					
					break;
				}

				//servidor.accept();
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {

				client.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block

			}
		}
	}
}
