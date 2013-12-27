package nl.pvanassen.ns.handle;

import java.io.InputStream;

public interface Handle<T> {

    /**
     * NS Date to Java date formatting string
     */
    String DATETIME_FORMAT = "yyyy-MM-dd'T'hh:mm:ssZ";

    T getModel(InputStream stream);
}
