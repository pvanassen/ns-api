package nl.pvanassen.ns.http;

import java.io.IOException;
import java.io.InputStream;

import nl.pvanassen.ns.error.NsApiException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnection {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final HttpClient httpclient;

	public HttpConnection(String username, String password) {
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(new AuthScope("webservices.ns.nl",
				80), new UsernamePasswordCredentials(username, password));
		httpclient = HttpClientBuilder.create()
				.setDefaultCredentialsProvider(credentialsProvider).build();
	}

	public InputStream getContent(String url) {
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			logger.info("Status: " + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return entity.getContent();
			}
			logger.error("Error while calling the webservice, entity is null");
			throw new NsApiException(
					"Error while calling the webservice, entity is null");
		} catch (ClientProtocolException e) {
			logger.error("Error while calling the webservice", e);
			throw new NsApiException("Error while calling the webservice", e);
		} catch (IOException e) {
			logger.error("Error while calling the webservice", e);
			throw new NsApiException("Error while calling the webservice", e);
		}
	}
}
