package nl.pvanassen.ns;

import java.io.IOException;
import java.io.InputStream;

import nl.pvanassen.ns.error.NsApiException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Internal class for handling http connections
 * 
 * @author Paul van Assen
 * 
 */
class HttpConnection {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final HttpClient httpclient;

    /**
     * Constructor specifying username and password
     * 
     * @param username Username for the API
     * @param password Password for the API
     */
    HttpConnection(String username, String password) {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope("webservices.ns.nl", 80), new UsernamePasswordCredentials(
                username, password));
        httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
    }

    /**
     * Handling the webservice call
     * 
     * @param url URL to call
     * @return Input stream as a result, or an exception
     * @throws IOException In case of an IO error
     * @throws NsApiException In case of any other error
     */
    InputStream getContent(String url) throws IOException, NsApiException {
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpclient.execute(httpget);
            logger.info("Status: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return entity.getContent();
            }
            logger.error("Error while calling the webservice, entity is null");
            throw new NsApiException("Error while calling the webservice, entity is null");
        }
        catch (IOException e) {
            logger.error("Error while calling the webservice", e);
            throw e;
        }
    }
}
