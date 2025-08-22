package app;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) throws IOException{
		String host = "localhost";
		int port = 4000;
		Socket socket = new Socket(host, port);
		
		Scanner scanner = new Scanner(System.in);
	}

}
