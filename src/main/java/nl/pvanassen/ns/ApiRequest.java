package nl.pvanassen.ns;

import java.io.Serializable;

import nl.pvanassen.ns.model.NsResult;

import org.jetbrains.annotations.NotNull;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Api request type. This type is the basis for all responses from the NS api.
 * 
 * @author Paul van Assen
 * 
 * @param <T> Result type
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ApiRequest<T extends NsResult> implements Serializable {

    /**
     * @return The path part of the request to make to the NS webservices
     */
    @NotNull
    abstract String getPath();

    /**
     * @return The querystring part of the request to make to the NS webservices
     */
    @NotNull
    abstract String getRequestString();
}
