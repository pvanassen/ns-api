package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.model.NsResult;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

/**
 * Main class for calling the NS api. The NS API is documented at <a href="http://www.ns.nl/api/api">NS API</a>
 * 
 * @author Paul van Assen
 * 
 */
public abstract class NsApi {

    private static final String WEBSERVICES_NS_URL = "http://webservices.ns.nl/";

    /**
     * NS Date to Java date formatting string
     */
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    /**
     * Method to instantiate the old NS api handle. Takes a username and password as parameters. A username/password can be
     * requested at <a href="http://www.ns.nl/api/api">NS API</a>
     *
     * @param username Username supplied by the NS
     * @param password Password supplied by the NS
     */
    public static NsApi getNsApiV2(@NotNull final String username, @NotNull final String password) {
        return getNsApiV2(username, password, WEBSERVICES_NS_URL);
    }

    public static NsApi getNsApiV2(@NotNull final String username, @NotNull final String password, @NotNull final String baseUrl) {
        return new NsApiV1(username, password, baseUrl);
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
    public final <T extends NsResult> T getApiResponse(@NotNull final ApiRequest<T> request) {
        try (final InputStream stream = getHttpConnection()
                .getContent(getBaseUrl() + request.getPath() + "?" + request.getRequestString())){

            final Handle<T> handle = getHandle(request.getClass());
            if (handle == null) {
                throw new NsApiException("Unknown request type " + request.getClass());
            }
            return handle.getModel(stream);
        }
        catch (IOException e) {
            throw new NsApiException("IO Exception occurred", e);
        }
    }

    protected abstract String getBaseUrl();

    protected abstract HttpConnection getHttpConnection();

    protected abstract <T extends NsResult> Handle<T> getHandle(Class<?> requestClass);
}
