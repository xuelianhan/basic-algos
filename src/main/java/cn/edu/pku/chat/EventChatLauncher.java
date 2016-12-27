package cn.edu.pku.chat;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class EventChatLauncher {
	
	public static void main(String[] args) {
		startServer();
	}
	
	private static void startServer() {
		Configuration config = new Configuration();
		config.setHostname(NetUtil.getLocalIp());
		config.setPort(9098);
		
		final SocketIOServer server = new SocketIOServer(config);
		server.addConnectListener(new ConnectListener () {
			public void onConnect(SocketIOClient client) {
				UUID clientId = client.getSessionId();
				System.out.println("Server received client connect session id:" + clientId.toString());
			}
		});
		server.addEventListener(EventName.push_event.getDesc(), ChatObject.class, new DataListener<ChatObject>() {
			public void onData(SocketIOClient client, ChatObject data, AckRequest ackSender) throws Exception {
				if (data == null || StringUtils.isEmpty(data.getUserName()) || StringUtils.isEmpty(data.getMessage())) {
					System.out.println("Client connect session parameters is insufficient:" + client.getSessionId());
					return;
				}
				//String sessionId = server.getConfiguration().getHostname()+":"+server.getConfiguration().getPort()+"_"+client.getSessionId();
				server.getBroadcastOperations().sendEvent(EventName.push_event.getDesc(), data);
			}
		});
		server.addDisconnectListener(new DisconnectListener() {
			public void onDisconnect(SocketIOClient client) {
				
			}
		});
		server.start();
	}
}
