package org.ict.algorithm.thread;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientUtilTest {

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setContentCompressionEnabled(false)
			.setConnectTimeout(100)
			.setConnectionRequestTimeout(500)
			.setSocketTimeout(2000)
			.build();
	
	private static SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true).build();
	
	private static PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
	 
	static {
		connManager.setMaxTotal(100);
	    connManager.setDefaultMaxPerRoute(50);
	}
	
	public static final CloseableHttpClient client = HttpClientBuilder.create()
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
