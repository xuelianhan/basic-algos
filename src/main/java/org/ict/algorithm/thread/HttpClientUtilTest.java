package org.ict.algorithm.thread;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

public class HttpClientUtilTest {

	public static RequestConfig requestConfig = RequestConfig.custom()
			.setContentCompressionEnabled(false)
			.setConnectTimeout(100)
			.setConnectionRequestTimeout(500)
			.setSocketTimeout(2000)
			.build();
	
	public static SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true).build();
	
	public static PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
	
	public static ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
	    @Override
	    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
	        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
	        while (it.hasNext()) {
	            HeaderElement he = it.nextElement();
	            String param = he.getName();
	            String value = he.getValue();
	            if (value != null && param.equalsIgnoreCase
	               ("timeout")) {
	                return Long.parseLong(value) * 1000;
	            }
	        }
	        return 5 * 1000;
	    }
	};
	 
	static {
		connManager.setMaxTotal(100);
	    connManager.setDefaultMaxPerRoute(50);
	}
	
	public static final CloseableHttpClient client = HttpClientBuilder.create()
			.setKeepAliveStrategy(keepAliveStrategy)
			.setConnectionManager(connManager)
			.setDefaultSocketConfig(socketConfig)
			.setDefaultRequestConfig(requestConfig)
			//.setMaxConnTotal(100)
			//.setMaxConnPerRoute(50)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
			.build();
	
	public static void main(String[] args) {
		// Registry schemeRegistry = RegistryBuilder.create().register("", "").build();
	    for(int i = 0; i < 10; i++) { 
		  SettableFuture<String> childFuture = SettableFuture.create();
		  Runnable r = new TestHttpGetThread("test"+ i, client,childFuture); 
		  Thread t = new Thread(r); 
		  t.start(); 
	    }
		 
	}
	
	
}
