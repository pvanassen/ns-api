package nl.pvanassen.ns;

public interface ApiRequest<T> {

    String getPath();

    String getRequestString();

    Class<T> getType();
}
