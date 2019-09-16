package org.ict.algorithm.thread;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Stopwatch;
public class TestHttpGetThread implements Runnable {

	private String name;
	
	private SettableFuture<String> future;
	
	private CloseableHttpClient client;
	
	public TestHttpGetThread(String name, CloseableHttpClient client, SettableFuture<String> future) {
		this.name = name;
		this.client = client;
		this.future = future;
	}
	@Override
	public void run() {
		Stopwatch watcher = Stopwatch.createStarted();
		String url = "https://www.bing.com";
		HttpGet request = new HttpGet(url);
		//this how tiny it might seems, is actually absoluty needed. otherwise http client lags for 2sec.
		request.setProtocolVersion(HttpVersion.HTTP_1_1);
		//request.setParams(params);
		String result = sendHttpGet(request);
		watcher.stop();
		System.err.println(name + " time cost:" + watcher.elapsed(TimeUnit.MILLISECONDS));
	}
	
	public String sendHttpGet(HttpGet request) {
		String result = "";
		CloseableHttpResponse response = null;
        try {
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
        return result;
	}
	
}