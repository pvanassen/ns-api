package nl.pvanassen.ns;

/**
 * Api request type. This type is the basis for all responses from the NS api.
 * 
 * @author Paul van Assen
 * 
 * @param <T> Result type
 */
public abstract class ApiRequest<T> {

    /**
     * Allow only instantiation in this package
     */
    ApiRequest() {
        super();
    }

    abstract String getPath();

    abstract String getRequestString();
}
