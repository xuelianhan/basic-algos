package org.ict.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class ApacheHttpClientExample {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://localhost:9090/SpringMVCExample";

	private static final String POST_URL = "http://localhost:9090/SpringMVCExample/home";

	public static void main(String[] args) {
		sendPOST();
	}

	private static void sendGET()  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(GET_URL);
		httpGet.addHeader("User-Agent", USER_AGENT);
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			System.out.println("GET Response Status:: "
					+ httpResponse.getStatusLine().getStatusCode());

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();

			// print result
			System.out.println(response.toString());
			httpClient.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendPOST() {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(POST_URL);
		httpPost.addHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("userName", "test"));

		try {
			HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
			httpPost.setEntity(postParams);

			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

			System.out.println("POST Response Status:: "
					+ httpResponse.getStatusLine().getStatusCode());

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();

			// print result
			System.out.println(response.toString());
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
