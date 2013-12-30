/**
 * 
 */
package nl.pvanassen.ns.error;

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
    public NsApiException(String message) {
        super(message);
    }

    /**
     * @param message Error message
     * @param cause Error cause
     */
    public NsApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
