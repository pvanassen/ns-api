package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.model.prijzen.PrijsHandle;
import nl.pvanassen.ns.model.reisadvies.ReisadviesHandle;
import nl.pvanassen.ns.model.stations.StationsHandle;
import nl.pvanassen.ns.model.storingen.StoringenHandle;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijdenHandle;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class for calling the NS api. The NS API is documented at <a href="http://www.ns.nl/api/api">NS API</a>
 * 
 * @author Paul van Assen
 * 
 */
public class NsApi {

    /**
     * NS Date to Java date formatting string
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private final HttpConnection httpConnection;

    private static final String BASE_URL = "http://webservices.ns.nl/";

    private final Map<Class<?>, Handle<?>> handleMap = new HashMap<>();

    /**
     * Constructor for the NS api handle. Takes a username and password as parameters. A username/password can be
     * requested at <a href="http://www.ns.nl/api/api">NS API</a>
     * 
     * @param username Username supplied by the NS
     * @param password Password supplied by the NS
     */
    public NsApi(String username, String password) {
        if (username == null || password == null) {
            throw new NullPointerException("Username or password cannot be null");
        }
        // No isEmpty to remain compatible with JDK 1.5
        if (username.trim().length() == 0 || password.trim().length() == 0) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        httpConnection = new HttpConnection(username, password);
        handleMap.put(ActueleVertrekTijdenRequest.class, new ActueleVertrekTijdenHandle());
        handleMap.put(StationsRequest.class, new StationsHandle());
        handleMap.put(StoringenEnWerkzaamhedenRequest.class, new StoringenHandle());
        handleMap.put(ReisadviesRequest.class, new ReisadviesHandle());
        handleMap.put(PrijzenRequest.class, new PrijsHandle());
    }

    /**
     * Method that makes a call to the NS. The request parameter defines what data to pull. The serialized data of the
     * request is returned, or an exception is thrown. For all request types, see <a href="http://www.ns.nl/api/api">NS
     * API</a> and {@link nl.pvanassen.ns.RequestBuilder}
     * 
     * @see nl.pvanassen.ns.RequestBuilder
     * @param request Data to request
     * @return Serialized response
     * @throws IOException In case of an network error
     * @throws NsApiException In case of any other error than a network error
     */
    public <T> T getApiResponse(ApiRequest<T> request) throws IOException, NsApiException {
        try (InputStream stream = httpConnection
                .getContent(NsApi.BASE_URL + request.getPath() + "?" + request.getRequestString())){
            @SuppressWarnings("unchecked")
            Handle<T> handle = (Handle<T>) handleMap.get(request.getClass());
            if (handle == null) {
                throw new NsApiException("Unknown request type " + request.getClass());
            }
            return handle.getModel(stream);
        }
    }

}
