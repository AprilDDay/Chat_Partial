package ie.gmit.dip;

import java.net.*;
import java.io.*;
import java.util.*;

public class GroupChat {
	
	//Variables for socket connection
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int port;

	
	//Variables for Group Chat
	private static final String TERMINATE = "/q";
	static String name;
	static volatile boolean finished = false;

	
	//to open socket connection
	public void startServer(int port){
		
		try {
			serverSocket  = new ServerSocket(port);
			while(true) {
				Socket Cl = serverSocket.accept();
				clientSocket = serverSocket.accept();
				PrintStream p = new PrintStream(Cl.getOutputStream());
				p.println("You connected to this server. -Bye-bye!");
				Cl.close();
			}
		}catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	public static void main(String[] args) {
		//if (args.length != 2)
		//	System.out.println("Two arguments required: <multicast-host> <port-number>");
		//else {
			try {
				//InetAddress group = InetAddress.getByName(args[0]);
				InetAddress group = InetAddress.getByName("224.0.0.0");
				//int port = Integer.parseInt(args[1]);
				int port = 1234;
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter your name: ");
				name = sc.nextLine();
				MulticastSocket socket = new MulticastSocket(port);

				// Since we are deploying
				socket.setTimeToLive(0);
				// this on localhost only (For a subnet set it as 1)

				socket.joinGroup(group);
				
				//create thread
				Thread t = new Thread(new ReadThread(socket, group, port));

				// Spawn a thread
				t.start();

				// sent to the current group
				System.out.println("Start typing messages...\n");
				while (true) {
					String message;
					message = sc.nextLine();
					if (message.equalsIgnoreCase(GroupChat.TERMINATE)) {
						finished = true;
						socket.leaveGroup(group);
						socket.close();
						break;
					}
					message = name + ": " + message;
					byte[] buffer = message.getBytes();
					DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
					socket.send(datagram);
				}
			} catch (SocketException se) {
				System.out.println("Error creating socket");
				se.printStackTrace();
			} catch (IOException ie) {
				System.out.println("Error reading or writing from or to socket");
				ie.printStackTrace();
			}
		}
	}
//}

class ReadThread implements Runnable {
	private MulticastSocket socket;
	private InetAddress group;
	private int port;
	private static final int MAX_LEN = 1000;

	ReadThread(MulticastSocket socket, InetAddress group, int port) {
		this.socket = socket;
		this.group = group;
		this.port = port;
	}

	@Override
	public void run() {
		while (!GroupChat.finished) {
			byte[] buffer = new byte[ReadThread.MAX_LEN];
			DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
			String message;
			try {
				socket.receive(datagram);
				message = new String(buffer, 0, datagram.getLength(), "UTF-8");
				if (!message.startsWith(GroupChat.name))
					System.out.println(message);
			} catch (IOException e) {
				System.out.println("Ciao! Socket closed.");
			}
		}
	}

}
