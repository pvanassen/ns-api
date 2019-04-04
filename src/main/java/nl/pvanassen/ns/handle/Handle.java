package nl.pvanassen.ns.handle;

import java.io.InputStream;

import nl.pvanassen.ns.model.NsResult;

import org.jetbrains.annotations.NotNull;

/**
 * This interface defines the way documents are deserialized.
 * 
 * @author Paul van Assen
 * 
 * @param <T> Return type
 */
public interface Handle<T extends NsResult> {

    /**
     * The deserialize method
     * 
     * @param stream Stream to deserialize
     * @return The deserialized type T
     */
    @NotNull
    T getModel(@NotNull final InputStream stream);
}
