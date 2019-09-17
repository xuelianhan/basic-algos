package org.ict.algorithm.thread;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.ict.algorithm.util.JsonUtil;

import com.google.common.base.Stopwatch;
public class TestHttpGetThread implements Runnable {

	private String name;
	
	private SettableFuture<String> future;
	
	private CompletableFuture<String> completeFuture;
	
	private CloseableHttpClient client;
	
	public TestHttpGetThread(String name, CloseableHttpClient client, SettableFuture<String> future) {
		this.name = name;
		this.client = client;
		this.future = future;
	}
	
	public TestHttpGetThread(String name, CloseableHttpClient client, CompletableFuture<String> completeFuture) {
		this.name = name;
		this.client = client;
		this.completeFuture = completeFuture;
	}
	
	public static void main(String[] args) {
		SettableFuture<String> childFuture = SettableFuture.create();
		TestHttpGetThread sender = new TestHttpGetThread("test", HttpClientUtilTest.client, childFuture);
		Thread t = new Thread(sender);
		t.start();
	}
	
	@Override
	public void run() {
		Stopwatch watcher = Stopwatch.createStarted();
		String url = "http://www.bing.com";
		HttpGet request = new HttpGet(url);
		//this how tiny it might seems, is actually absoluty needed. otherwise http client lags for 2sec.
		request.setProtocolVersion(HttpVersion.HTTP_1_1);
		//request.setConfig(HttpClientUtilTest.requestConfig);
		//request.setParams(params);
		String result = sendHttpGet(request);
		watcher.stop();
		System.err.println(name + " time cost:" + watcher.elapsed(TimeUnit.MILLISECONDS));
	}
	
	public String sendHttpGet(HttpGet request) {
		Stopwatch watcher = Stopwatch.createStarted();
		String result = "";
		CloseableHttpResponse response = null;
        try {
        	//System.out.println("headers:" + Arrays.toString(request.getAllHeaders()));
        	response = client.execute(request);
        	int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code: " + statusCode);
            }
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            future.setV(result);
		} catch (IOException e) {
			future.setV(null);
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        watcher.stop();
		System.err.println(name + " http get and set time cost:" + watcher.elapsed(TimeUnit.MILLISECONDS));
        return result;
	}
	
}