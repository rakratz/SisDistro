package app;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Pessoa;

public class Cliente {

	public static void main(String[] args) {
		 String host = "localhost";
	     int porta = 12345;
	     
	     try (Socket socket = new Socket(host, porta)){
	    	 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	    	Pessoa p1 = new Pessoa("Ricardo Kratz", 50);
	    	oos.writeObject(p1);
	    	oos.flush();
	    	oos.close();
	    	
	     } catch (IOException e) {
	            e.printStackTrace();
	     }

	}

}
