/*package ie.gmit.dip;

import java.net.*;

import java.io.*;

public class ChatClient{

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void startClientConnection(String ip, int port) throws IOException{
	
		try {
			//Socket_to_sean = new Socket("www.sean", 80);
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch (UnknownHostException e) {
			System.err.println(e);
		}
	}
	
	public String sendMessage(String msg) throws IOException{
		out.println(msg);
		String resp = in.readLine();
		return resp;
	}
	
	public void stopConnection() throws IOException{
		in.close();
		out.close();
		clientSocket.close();
	}

}
*/