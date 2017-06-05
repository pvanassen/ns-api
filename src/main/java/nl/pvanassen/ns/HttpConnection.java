package nl.pvanassen.ns;

import lombok.extern.slf4j.Slf4j;
import nl.pvanassen.ns.error.NsApiException;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import java.io.IOException;
import java.io.InputStream;

/**
 * Internal class for handling http connections
 * 
 * @author Paul van Assen
 * 
 */
@Slf4j
class HttpConnection {
    private final OkHttpClient client;

    /**
     * Constructor specifying username and password
     * 
     * @param username Username for the API
     * @param password Password for the API
     */
    HttpConnection(final String username, final String password) {
        client = new OkHttpClient.Builder().authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
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
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            if (response.body() == null) {
                log.error("Error while calling the webservice, entity is null");
                throw new NsApiException("Error while calling the webservice, entity is null");
            }
            return response.body().byteStream();
        }
        catch (RuntimeException e) {
            log.error("Error while calling the webservice, entity is null");
            throw new NsApiException("Error while calling the webservice, entity is null", e);
        }
    }
}
