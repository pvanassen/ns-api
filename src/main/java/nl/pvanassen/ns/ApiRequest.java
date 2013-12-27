package nl.pvanassen.ns;

public abstract class ApiRequest<T> {
    /**
     * Allow only instantiation in this package
     */
    ApiRequest() {
        super();
    }
    
    abstract String getPath();

    abstract String getRequestString();

    abstract Class<T> getType();
}
