package cn.edu.pku.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Compilation:
 * Execution:
 * Dependencies: ClientMain.java Server.java
 * 
 * 
 * This class creates a new client that can connect and interact with the server
 * in the following ways:
 * 
 * 1.Send new connections to server.
 * 2.Accept user input and handle sending to the server.
 * 3.Handle responses from server.
 * 4.Ask the server how long the server has been running for and receive the correct
 * response.
 * 5.Ask the server how long the client has been in the chat room for and receive the
 * correct response.
 * 6.Ask the server's IP address and receive the correct response.
 * 7.Ask the server how many clients in total are currently connected to the chat room
 * and receive the correct response.
 * 8.Ask the server for a list of request commands that can be sent and receive the
 * correct response.
 * 9.Broadcasting messages from each client can be handled by server.
 * 10.Clients will be asked for usernames by server after connecting to it.
 * 11.Clients can be chat in chat rooms after a username has been selected.
 * 12.The usernames of clients should be unique to the chat room.
 * 13.Clients can send and listen for messages concurrently.
 * 14.Clients can handle the case of server-side offline in graceful way.
 * 15.Server can handle the case of client-side disconnecting in graceful way.
 * 16.Clients can send a disconnect request to server and server handles this gracefully.
 * 17.When a client connects or disconnects from chat room, the rest of other clients can
 * be noticed for this.
 * 18.All input/output should be handled accordingly through the correct use of Exception
 * handling.
 * 19.Before connecting to a server, a client should firstly be asked for what the server 
 * address it wants to connect.
 * 20.When a client sends specific requests to server, this interactions should not be 
 * broadcasted to other clients in chat room and should only be visible for this client and
 * the server.
 * 
 * @author 
 *
 */
public class ClientInstance implements Runnable {
	
	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private Socket socket;
	
	private BufferedReader brConsole;
	
	private PrintWriter pw = null;
	
	private Thread thread = null;
	
	private ClientHandler handler = null;
	
	private String clientName;
	
	public ClientInstance(String host, int port) {
		try {
			System.out.println("Client connect host: " + host + ",port: " + port);
			socket = new Socket(host, port);
			start();
	        open();
	        String line = "";
	        System.out.println("client read line: " + line);
	        boolean flag = false;
	        while (!flag) {
	        	try {
	        		line = brConsole.readLine();
	        		System.out.println("Client has received input from console: " + line);
	        		pw.write(line + LINE_SEPARATOR);
	        		pw.flush();
	        		System.out.println("Client sends message of: " + line);
	        		flag = line.equals("bye");
	            } catch(IOException ioe) {
	            	System.out.println("Client Sending error: " + ioe.getMessage());
	            	flag = true;
	            }
	        }
	        System.out.println("client start to close for input line: " + line);
	        close();
	        System.exit(0);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO exception: " + e.getMessage());
		}
	}
	
	public void run() {
		if (thread != null) {
			System.out.println("Establishing connection. Please wait ...");
			addClientHandler(socket);
		}
	}
	
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void addClientHandler(Socket socket) {
		System.out.println("Connected: " + socket);
		handler = new ClientHandler(this, socket);
		try {
			handler.openIO();
			handler.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ClientHandler extends Thread {
		private static final String SERVER_HEAD_MSG = "_Server:";
		private ClientInstance client;
		
		private Socket socket;
		
		private int handlerId;
		
		private BufferedReader brHandler;
		
		private PrintWriter pwHandler = null;
		
		ClientHandler(ClientInstance client, Socket socket) {
			this.client = client;
			this.socket = socket;
			this.handlerId = socket.getLocalPort();
		}
		
		public void run() {
        	try {
        		String line = "";
        		while ((line = brHandler.readLine()) != null) {
        			if (line.startsWith("Command:")) {
        				printMessage(line);
        			} else {
        				printMessage(line);
        				pwHandler.write(line+LINE_SEPARATOR);
    	        		pwHandler.flush();
    	        		System.out.println("Client sends message of: " + line);
        			}
        		}
            } catch(IOException ioe) {
            	System.out.println("Client Sending error: " + ioe.getMessage());
            }
		}
		
		public void printMessage(String message) {
			String msg = "";
			int index = message.indexOf(SERVER_HEAD_MSG);
			if (index >= 0) {
				msg = message.substring(index + 8);
				System.out.println("Client" + handlerId + " has received message from Server: " + msg);
			} else {
				System.out.println("Client" + handlerId + " has received message from Server: " + message);
			}
		}
		
		public void openIO() throws IOException {
			brHandler = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    pwHandler = new PrintWriter(socket.getOutputStream());
		}
		
		public void send(String message) {
			try {
				pwHandler.write(message);
				pwHandler.flush();
				System.out.println("Client sends message of: " + message);
			} catch (Exception e) {
				System.err.println("client send message error.");
			}
		}
	}
	
	public void open() throws IOException {  
		brConsole = new BufferedReader(new InputStreamReader(System.in));
	    pw = new PrintWriter(socket.getOutputStream());
	}
	
	public void close() {
		try{ 
			System.out.println("Client: " + clientName + " is preparing to exit...");
			if (brConsole != null)brConsole.close();
	        if (pw != null)pw.close();
	        //if (socket != null)socket.close();
	        System.out.println("Client: " + clientName + " exit successful...");
	    } catch(IOException ioe){  
	    	System.out.println("Error closing ...");
	    }
	}

	
}
