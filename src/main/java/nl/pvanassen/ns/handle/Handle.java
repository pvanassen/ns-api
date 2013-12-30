package nl.pvanassen.ns.handle;

import java.io.InputStream;

/**
 * This interface defines the way documents are deserialized. 
 * 
 * @author Paul van Assen
 *
 * @param <T> Return type
 */
public interface Handle<T> {

    /**
     * The deserialize method
     * @param stream Stream to deserialize
     * @return The deserialized type T
     */
    T getModel(InputStream stream);
}
