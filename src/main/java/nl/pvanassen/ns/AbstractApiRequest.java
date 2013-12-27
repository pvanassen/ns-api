package nl.pvanassen.ns;

public abstract class AbstractApiRequest<T> {

    abstract String getPath();

    abstract String getRequestString();

    abstract Class<T> getType();
}
