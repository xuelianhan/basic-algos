package cn.edu.pku.chat;


/**
 * Compilation:
 * $ cd chat-client/src/main/java
 * $ javac cn/edu/pku/chat/ClientInstance.java
 * $ javac cn/edu/pku/chat/ClientMain.java 
 * 
 * Execution:
 * $java cn/edu/pku/chat/ClientMain 127.0.0.1 8090 ClientA
 * Dependencies:
 * 
 * This class contains a main method that instantiate a new client Instance.
 * 
 * @author 
 */
public class ClientMain {
	
	public static void main(String[] args) {
		ClientInstance client = null;
		System.out.println("At first, Use java packagename.classname host port clientname to connect server.");
		System.out.println("Command format is as following:");
		System.out.println("Usage: java cn/edu/pku/chat/ClientMain host port clientName");
		System.out.println("Usage: get_server_address");
		System.out.println("Usage: get_server_run_time");
		System.out.println("Usage: get_number_of_clients");
		System.out.println("Usage: get_how_long_in_chat_room");
		System.out.println("Usage: point_to_point:clientName:message");
		System.out.println("Usage: bye");
		System.out.println("Usage: help");
		if (args.length != 2) {
			System.out.println("Usage: java cn/edu/pku/chat/ClientMain host port");
		} else {
			client = new ClientInstance(args[0], Integer.valueOf(args[1]));
		}
	}
}
