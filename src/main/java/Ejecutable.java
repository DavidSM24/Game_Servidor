import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import DAOs.ShopDAO;
import DAOs.UserDAO;
import models.User;
import models.requests.Request;
import models.requests.Request_Login;
import models.responses.Response_Login;

public class Ejecutable implements Runnable{

	String prueba="prueba";
	public static ServerSocket servidor;
	
	public static void main(String[] args) {
		Ejecutable ej=new Ejecutable();
		ej.run();
	}

	public void run() {
		// TODO Auto-generated method stub
		
		Socket client=null;
		
		try {
			client = servidor.accept();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {

			while(true) {
				
				ObjectInputStream flujoentrada=new ObjectInputStream(client.getInputStream());
				
				Request request=(Request) flujoentrada.readObject();
				
				//switch
				
				switch(request.getName()) {
					case "login":
						
						Request_Login rl=(Request_Login) request;
						User u=UserDAO.getByUsername(rl.getUsername());
						
						boolean correct=false;
						
						if(u!=null) {
							if(rl.getPassword().equals(u.getPassword())) {
								correct=true;	
							}
						}
						
						Response_Login rpl;
						
						if(correct) {
							rpl=new Response_Login(true, u, ShopDAO.getShop());
						}
						else {
							rpl=new Response_Login(false, null, null);
						}
						
						ObjectOutputStream flujosalida = new ObjectOutputStream(client.getOutputStream());
	
						flujosalida.writeObject(rpl);
						break;
					
					case "register":
						
				}
				
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				
				servidor.close();
				client.close();
				servidor = new ServerSocket(6666);
				Thread miHilo=new Thread(this);
				miHilo.start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}
		}
	}
}
