package cn.edu.pku.chat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetUtil {
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
}
