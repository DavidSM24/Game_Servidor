import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import models.requests.Request_Edit;
import models.requests.Request_Login;
import models.requests.Request_Register;
import models.responses.Response_Buy;
import models.responses.Response_CreateGame;
import models.responses.Response_Login;

public class Ejecutable extends Thread {
	
	public static ServerSocket servidor;

	public static void main(String[] args) {
			
		try {
			servidor=new ServerSocket(6666);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ejecutable ej = new Ejecutable();
		ej.start();
	}

	public void run() {
		// TODO Auto-generated method stub

		Socket client = null;

		try {
			client = servidor.accept();
			System.out.println("hola");
			
			Ejecutable ej=new Ejecutable();
			ej.start();
			
			System.out.println("despues del run");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {

			while (true) {
				
				System.out.println("\nesperando request...\n");
				
				ObjectInputStream flujoentrada = new ObjectInputStream(client.getInputStream());
				boolean accepted;
				
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
									
					boolean correct = false;

					if (u != null) {
						if (rl.getPassword().equals(u.getPassword())) {
							correct = true;
						}
					}

					System.out.println(u);
					if (correct) {
						rpl = new Response_Login(true, u, ShopDAO.getShop());
					} else {
						rpl = new Response_Login(false, null, null);
					}

					//System.out.println(rpl);
					
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
						
						if(!(UserDAO.getByUsername(rr.getUsername())!=null)) {
							
							
							System.out.println(UserDAO.getByUsername(rr.getUsername()));
							
							UserDAO.insert(newUser);

							accepted = true;
						}	
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
					
					System.out.println(rb.getUser().getUsername());
					
					double total=0;
					
					if(rb.getGames()!=null&&rb.getGames().size()>0) {
						for(Game eg:rb.getGames()) {
							total+=eg.getPrice();
						}
						
						
						if(rb.getUser()!=null&&rb.getGames()!=null&&rb.getUser().getMoney()>=total) {
							
							for(Game g2:rb.getGames()) {
								UserDAO.buyGame(rb.getUser(), g2);
							}
							
							rb.getUser().setMoney(rb.getUser().getMoney()-total);
							
							UserDAO.update(rb.getUser());
							
							rb.getUser().getGames().add(rb.getGames().get(0));
							
							accepted=true;
						}
					}
					
					Response_Buy rpb=new Response_Buy(accepted,rb.getUser());
					System.out.println(rpb);
					flujosalida = new ObjectOutputStream(client.getOutputStream());
					flujosalida.writeObject(rpb);
					
					
					break;
					
				case "edit":
					accepted=false;
					Request_Edit re=(Request_Edit) request;
					
					if(
							re.getUser()!=null
							&&UserDAO.getById(re.getUser().getId())!=null) {
						
						UserDAO.update(re.getUser());
						
						accepted=true;
						
					}
					
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
