package cn.edu.pku.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
<<<<<<< HEAD
import java.util.concurrent.atomic.AtomicInteger;
=======
>>>>>>> 3f799458... add Server-Client socket samples

/**
 * Compilation: 
 * $ cd chat-server/src/main/java
 * $ javac cn/edu/pku/chat/Server.java 
 * 
 * Execution: 
 * java cn/edu/pku/chat/Server 8090
 * 
 * Dependencies:none
 * 
 * This class create a new server that can accept connections from new clients.
<<<<<<< HEAD
 * It also contains an embedded class which extends the Thread base class to
=======
 * It also contains an embedded class whitch extends the Thread base class to
>>>>>>> 3f799458... add Server-Client socket samples
 * handle multiple clients concurrently.Server can interact with clients in
 * broadcast mode. Server has following features: 1.Listen for new connections
 * from clients. 2.Handle connections from clients. 3.Respond to clients
 * requests. 4.Broadcast chat messages to all clients.
 * 
 * @author
 *
 */
public class Server implements Runnable {
	
	private static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private ServerHandler[] handlers = new ServerHandler[100];
	
	private Thread thread = null;
	
<<<<<<< HEAD
	private AtomicInteger handlerCount = new AtomicInteger(0);
=======
	private int handlerCount = 0;
>>>>>>> 3f799458... add Server-Client socket samples

	private ServerSocket server = null;

	private String host;
	
	private long start = 0;
	
	private Set<String> clientNames = new ConcurrentSkipListSet<String>();
	
	private Map<String, Integer> clientNameHandlerId = new ConcurrentSkipListMap<String, Integer>();
	
	/**
	 * construct a socket connection
	 * @param host
	 * @param port
	 */
	public Server(String host, int port) {
		try {
			start = System.currentTimeMillis();
			this.host = host;
			server = new ServerSocket(port);
			System.out.println("Server started: " + server + " on host: " + host + " of port " + port);
			startServerThread();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * start main server thread
	 */
	public void startServerThread() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	/**
	 * main server thread implementation
	 */
	public void run() {
		while (thread != null) {
			System.out.println("Waiting for clients to connect...");
			try {
				addServerHandler(server.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * dispatch socket to one handler
	 * @param socket
	 */
	private void addServerHandler(Socket socket) {
<<<<<<< HEAD
		if (handlerCount.get() >= handlers.length) {
			System.out.println("Server handlers number is overflow!" + handlers.length + "is allowed at max.");
		} 
		System.out.println("Server Accepted client: " + socket);
		handlers[handlerCount.get()] = new ServerHandler(this, socket);
		try {
			handlers[handlerCount.get()].open();
			handlers[handlerCount.get()].start();
			handlerCount.getAndIncrement();
			if (handlers[handlerCount.get()] != null) {
				if (handlers[handlerCount.get()].getClientName() == null || "".equals(handlers[handlerCount.get()].getClientName())) {
					handleMessage(handlers[handlerCount.get()].getHandlerId(), "need_select_client_name");
=======
		if (handlerCount >= handlers.length) {
			System.out.println("Server handlers number is overflow!" + handlers.length + "is allowed at max.");
		} 
		System.out.println("Server Accepted client: " + socket);
		handlers[handlerCount] = new ServerHandler(this, socket);
		try {
			handlers[handlerCount].open();
			handlers[handlerCount].start();
			handlerCount++;
			if (handlers[handlerCount] != null) {
				if (handlers[handlerCount].getClientName() == null || "".equals(handlers[handlerCount].getClientName())) {
					handleMessage(handlers[handlerCount].getHandlerId(), "need_select_client_name");
>>>>>>> 3f799458... add Server-Client socket samples
				}
			}
		} catch (IOException e) {
			System.err.println("add server handler error!");
		}
	}
	
	/**
	 * search handler by port number
	 * @param handlerId
	 * @return
	 */
	private int findHandler(int handlerId) {
<<<<<<< HEAD
		for (int i = 0; i < handlerCount.get(); i++) {
=======
		for (int i = 0; i < handlerCount; i++) {
>>>>>>> 3f799458... add Server-Client socket samples
			if (handlers[i].getHandlerId() == handlerId) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * message send dispatch
	 * @param id
	 * @param message
	 */
	public synchronized void handleMessage(int id, String message) {
		System.out.println("step into handleMessage, id:"+id+",message:"+message);
		int index = findHandler(id);
		System.out.println("Find server-handler of  " + id + " at location of " + index);
		if ("bye".equals(message)){
			System.out.println("bye message");
			if (index >= 0) {
<<<<<<< HEAD
				for (int i = 0; i < handlerCount.get(); i++) {
=======
				for (int i = 0; i < handlerCount; i++) {
>>>>>>> 3f799458... add Server-Client socket samples
					if (i != index) {
						System.out.println("server start to notice client " + id + " offline message to group members.");
						handlers[i].send("Command:" + CommandType.command_separator.getDesc()+"Notice:client" + id + " offline by sending command " + CommandType.bye.getDesc()+ LINE_SEPARATOR);
						System.out.println("server-handler of  " + id + " at location of " + index);
					}
				}
				System.out.println("start to remove server-handler of  " + id + " at location of " + index);
				removeHandler(index);
				System.out.println("end to remove server-handler of  " + id + " at location of " + index);
			}
		} else if ("need_select_client_name".equals(message)) {
			System.out.println("need_select_client_name message");
			if (index >= 0) {
				handlers[index].send("Command:" + CommandType.command_separator.getDesc()+ "Please input a client name:" + LINE_SEPARATOR);
			}
		} else if ("duplicated_client_name".equals(message)) {
			System.out.println("duplicated_client_name message");
			if (index >= 0) {
				handlers[index].send("Command:" + CommandType.command_separator.getDesc()+ "This name has been used.Please select another client name:" + LINE_SEPARATOR);
			}
		} else if (message.startsWith("point_to_point")) {
			System.out.println("point_to_point message:" + message);
			String[] msgs = message.split(":");
			String clientPointName = msgs[1];
			String msg = msgs[2];
			int handlerId = clientNameHandlerId.get(clientPointName);
			System.out.println("point_to_point clientPointName:" + clientPointName + ", handlerId:"+handlerId);
			int idx = findHandler(handlerId);
			System.out.println("point_to_point clientPointName:" + clientPointName + ", handlerId:"+handlerId+" find at location:"+idx);
			if (idx >= 0) {
				handlers[idx].send("Command:" + CommandType.command_separator.getDesc()+ msg + LINE_SEPARATOR);
			}
		} else if ("".equals(message)) {
			System.out.println("server received empty message!");
		} else {
			System.out.println("group_chat,handlerCount:"+handlerCount+",message:"+message);
<<<<<<< HEAD
			for (int i = 0; i < handlerCount.get(); i++) {
=======
			for (int i = 0; i < handlerCount; i++) {
>>>>>>> 3f799458... add Server-Client socket samples
				if (i != index) {
					handlers[i].send("Command:" + CommandType.command_separator.getDesc() + message + LINE_SEPARATOR);
				}
			}
		}
	}
	
	/**
	 * remove handler by port number
	 * @param id
	 */
	public synchronized void removeHandler(int id) {
		int index = findHandler(id);
		if (index >= 0) {
			ServerHandler handler = handlers[index];
			
			System.out.println("Start to remove handler of id:" + id + " at position: " + index);
<<<<<<< HEAD
			if (index < (handlerCount.get() - 1)) {
				for (int i = (index + 1); i < handlerCount.get(); i++) {
=======
			if (index < (handlerCount - 1)) {
				for (int i = (index + 1); i < handlerCount; i++) {
>>>>>>> 3f799458... add Server-Client socket samples
					handlers[i - 1] = handlers[i];
				}
			}
			System.out.println("Start to remove according clientName:" + handler.getClientName());
			clientNames.remove(handler.getClientName());
			clientNameHandlerId.remove(handler.getClientName());
			System.out.println("handlerCount before:" + handlerCount);
<<<<<<< HEAD
			handlerCount.getAndDecrement();
=======
			handlerCount--;
>>>>>>> 3f799458... add Server-Client socket samples
			System.out.println("handlerCount after:" + handlerCount);
			try {
				handler.close();
			} catch (IOException e) {
				System.err.println("Error Close ServerHandler id " + id);
			}
		}
	}
	
	class ServerHandler extends Thread {
		private Socket socket = null;
		
		private Server server = null;
		
		private int handlerId  = -1;

		private BufferedReader br;
		
		private PrintWriter pw = null;
		
		private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
		
		private String clientName = null;
		
		public ServerHandler(Server server, Socket socket) {
			this.server = server;
			this.socket = socket;
			this.handlerId = socket.getPort();
		}
		
		public long getStartTime() {
			return startTime.get();
		}
		
		public void run() {
			try {
				String line = "";
				String message = "";
				startTime.set(System.currentTimeMillis());
				while ((line = br.readLine()) != null) {
					System.out.println("ServerHandler" + handlerId +" has received message from client" + handlerId + ",message content:" + line);
					if (clientName == null) {
						System.out.println("clientName is null");
						handleMessage(handlerId, "need_select_client_name");
						System.out.println("clientNames set:"+clientNames.toString());
						if (clientNames.contains(line)) {
							System.out.println("clientName duplicated:"+line);
							message = "duplicated_client_name";
							handleMessage(handlerId, message);
							System.out.println("clientName duplicated message has send:"+ message);
						} else {
							clientName = line.trim();
							clientNames.add(clientName);
							clientNameHandlerId.put(clientName, handlerId);
							System.out.println("put clientName: "+clientName+" and handlerId: "+handlerId+" in set and map:");
						}
					} else {
						if (line.equals("get_server_address")) {
							message = "Command:" + CommandType.get_server_address.getDesc() + CommandType.command_separator.getDesc() + " my address is:" + host + LINE_SEPARATOR;
							send(message);
						} else if (line.equals("get_server_run_time")) {
							message = "Command:" + CommandType.get_server_run_time.getDesc() + CommandType.command_separator.getDesc() + " my runtime is:" + (System.currentTimeMillis() - start)/1000 +" seconds" + LINE_SEPARATOR;
							send(message);
						} else if (line.equals("get_number_of_clients")) {
							message = "Command:" + CommandType.get_number_of_clients.getDesc() + CommandType.command_separator.getDesc() + " number of clients now in chat room is :" + handlerCount + LINE_SEPARATOR;
							send(message);
						} else if (line.equals("get_how_long_in_chat_room")) {
							message = "Command:" + CommandType.get_how_long_in_chat_room.getDesc() + CommandType.command_separator.getDesc() + " you have stay in chat room for " + (System.currentTimeMillis() - startTime.get())/1000 +" seconds" + LINE_SEPARATOR;
							send(message);
						} else if (line.equals("help")) {
							message = "Command:" + CommandType.command_separator.getDesc()  +" " + CommandType.getAllCommands() + LINE_SEPARATOR;
							send(message); 
						} else if (line.equals("bye")){
							message = "bye";
							handleMessage(handlerId, message);
						} else if (line.startsWith("point_to_point")) {//point-to-point
							message = line;
							handleMessage(handlerId, message);
						} else {//group chat
							message = line;
							handleMessage(handlerId, message);
						}
					}
					pw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void send(String message) {
			try {
				pw.write(message);
				pw.flush();
				System.out.println("Server has sent message: " + message);
			} catch (Exception e) {
				System.err.println("server send message error.");
				server.removeHandler(handlerId);
				System.err.println("server-handler-" + handlerId + "has been removed from server-handler-pools.");
			}
		}
		
		public void open() throws IOException {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    pw = new PrintWriter(socket.getOutputStream());
		}
		
		public void close() throws IOException {
			if (br != null) br.close();
			if (pw != null) pw.close();
			if (socket != null)socket.close();
		}

		public int getHandlerId() {
			return handlerId;
		}

		public String getClientName() {
			return clientName;
		}

		public void setClientName(String clientName) {
			this.clientName = clientName;
		}

	}

	public static String getLocalIp() {
		String ip = "127.0.0.1";
	    try {
	        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	        while (interfaces.hasMoreElements()) {
	            NetworkInterface iface = interfaces.nextElement();
	            if (iface.isLoopback() || !iface.isUp())
	                continue;

	            Enumeration<InetAddress> addresses = iface.getInetAddresses();
	            while(addresses.hasMoreElements()) {
	                InetAddress addr = addresses.nextElement();
	                ip = addr.getHostAddress();
	                if (ip.contains(":")) {
	                	continue;
	                }
	            }
	        }
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return ip;
	}
	
	public enum CommandType {
		get_server_address(90, "get_server_address"),
		get_server_run_time(91, "get_server_run_time"),
		get_number_of_clients(92, "get_number_of_clients"),
		get_how_long_in_chat_room(93, "get_how_long_in_chat_room"),
		bye(94,"bye"),
		command_separator(95, "_Server:"),
		point_to_point(96, "point_to_point"),
		group_chat(97, "group_chat"),
		input_client_name(98, "input_client_name");
		
		CommandType(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		
		private int code;
		
		private String desc;

		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
		
		public static CommandType codeOf(int code) {
			for (CommandType type : values()) {
				if (code == type.getCode()) {
					return type;
				}
			}
			return null;
		}
		
		public static String getAllCommands() {
			Map<Integer, String> commandMap = new HashMap<Integer, String>();
			for (CommandType type : values()) {
				if (type != CommandType.command_separator) {
					commandMap.put(type.getCode(), type.getDesc());
				}
			}
			return commandMap.toString();
		}
		
	}

	public static void main(String[] args) {
		Server ser = null;
		String host = getLocalIp();
		if (args.length != 1) {
			System.out.println("Usage: java cn/edu/pku/chat/Server port");
		} else {
			ser = new Server(host, Integer.parseInt(args[0]));
		}
	}

	
}
