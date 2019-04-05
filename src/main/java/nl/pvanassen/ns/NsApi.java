package nl.pvanassen.ns;

import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.model.NsResult;
import nl.pvanassen.ns.model.prijzen.PrijsHandle;
import nl.pvanassen.ns.model.reisadvies.ReisadviesHandle;
import nl.pvanassen.ns.model.stations.StationsHandle;
import nl.pvanassen.ns.model.storingen.StoringenHandle;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijdenHandle;

import org.jetbrains.annotations.NotNull;

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
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    private final HttpConnection httpConnection;

    private final String baseUrl;

    private final Map<Class<?>, Handle<?>> handleMap = new HashMap<>();

    /**
     * Constructor for the NS api handle. Takes a username and password as parameters. A username/password can be
     * requested at <a href="http://www.ns.nl/api/api">NS API</a>
     * 
     * @param username Username supplied by the NS
     * @param password Password supplied by the NS
     */
    public NsApi(@NotNull final String username, @NotNull final String password) {
        this(username, password, "http://webservices.ns.nl/");
    }

    /**
     * Constructor for the NS api handle. Takes a username and password as parameters. A username/password can be
     * requested at <a href="http://www.ns.nl/api/api">NS API</a>
     *
     * @param username Username supplied by the NS
     * @param password Password supplied by the NS
     */
    public NsApi(@NotNull final String username, @NotNull final String password, @NotNull final String baseUrl) {
        // No isEmpty to remain compatible with JDK 1.5
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        httpConnection = new HttpConnection(username, password);
        handleMap.put(ActueleVertrekTijdenRequest.class, new ActueleVertrekTijdenHandle());
        handleMap.put(StationsRequest.class, new StationsHandle());
        handleMap.put(StoringenEnWerkzaamhedenRequest.class, new StoringenHandle());
        handleMap.put(ReisadviesRequest.class, new ReisadviesHandle());
        handleMap.put(PrijzenRequest.class, new PrijsHandle());
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl;
        }
        else {
            this.baseUrl = baseUrl.concat("/");
        }
    }

    /**
     * Method that makes a call to the NS. The request parameter defines what data to pull. The serialized data of the
     * request is returned, or an exception is thrown. For all request types, see <a href="http://www.ns.nl/api/api">NS
     * API</a> and {@link nl.pvanassen.ns.RequestBuilder}
     * 
     * @see nl.pvanassen.ns.RequestBuilder
     * @param request Data to request
     * @param <T> Type of response
     * @return Serialized response
     * @throws NsApiException In case of any other error than a network error
     */
    @NotNull
    public <T extends NsResult> T getApiResponse(@NotNull final ApiRequest<T> request) {
        try (InputStream stream = httpConnection
                .getContent(baseUrl + request.getPath() + "?" + request.getRequestString())){
            @SuppressWarnings("unchecked")
            Handle<T> handle = (Handle<T>) handleMap.get(request.getClass());
            if (handle == null) {
                throw new NsApiException("Unknown request type " + request.getClass());
            }
            return handle.getModel(stream);
        }
        catch (IOException e) {
            throw new NsApiException("IO Exception occurred", e);
        }
    }

}
