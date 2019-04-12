package nl.pvanassen.ns.parser;

import org.jetbrains.annotations.NotNull;

/**
 * Xml handling base class
 * 
 * @author Paul van Assen
 * 
 */
public interface XmlResponse extends Response<XmlResponse> {

    /**
     * Get the value of an attribute
     * 
     * @param name Name of the attribute to get
     * @return Value of the attribute if found, or an exception of not found
     */
    String attr(@NotNull String name);
}
