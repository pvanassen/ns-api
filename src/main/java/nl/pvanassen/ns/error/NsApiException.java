package nl.pvanassen.ns.error;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when the API fails
 * 
 * @author Paul van Assen
 * 
 */
public class NsApiException extends RuntimeException {

    private static final long serialVersionUID = -4535400016864458414L;

    /**
     * @param message Error message
     */
    public NsApiException(@NotNull final String message) {
        super(message);
    }

    /**
     * @param message Error message
     * @param cause Error cause
     */
    public NsApiException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

}
