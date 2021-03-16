/*package ie.gmit.dip;

import java.io.*;
import java.net.*;

public class ChatServer{
	private ServerSocket serverSocket;
	
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int port;
	
	public void startServer(int port){
	
	try {
		
		serverSocket  = new ServerSocket(port);
		while(true) {
			Socket Cl = serverSocket.accept();
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String greeting = in.readLine();
			if ("hello server".equals(greeting)) {
				out.println("hello client");
			} else {
				out.println("unrecognised greeting");
				}
			//PrintStream p = new PrintStream(Cl.getOutputStream());
			//p.println("You connected to this server. -Bye-bye!");
			//Cl.close();
		}
	}catch (IOException e) {
		System.out.println(e);
	}
	}
	
	public void stopServer() throws IOException{
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
		
	}
	
}
*/