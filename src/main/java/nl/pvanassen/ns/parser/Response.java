package nl.pvanassen.ns.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Xml handling base class
 * 
 * @author Paul van Assen
 * 
 */
public interface Response<T extends Response> {

    /**
     * Get the XML root element
     * 
     * @param stream Stream to parse
     * @param rootName Root element name
     * @return Xml object
     */
    static XmlResponsePresent getXml(InputStream stream, String rootName) {
        return new XmlResponsePresent(stream, rootName);
    }

    static JsonResponsePresent getJson(InputStream stream) {
        return new JsonResponsePresent(stream);
    }
    /**
     * @return Name of the element
     */
    @NotNull
    String name();

    /**
     * @return Content of the element
     */
    @Nullable
    String content();

    /**
     * Get a child element
     * 
     * @param name Name of the child
     * @return Xml implementation, either present or absent
     */
    @NotNull
    T child(@NotNull String name);

    /**
     * Get a child element
     *
     * @param name Name of the child
     * @return Xml implementation, either present or absent
     */
    @NotNull
    ResponsePresent<T> requiredChild(@NotNull String name);

    /**
     * Get all child elements by name
     * 
     * @param name Name of the child elements
     * @return A list of XML objects, either present or absent
     */
    @NotNull
    List<T> children(@NotNull String name);

    /**
     * Checks to see if an element is present
     * 
     * @param name The name of the element to check
     * @return True if present
     */
    boolean isPresent(@NotNull String name);

    /**
     * Returns an optional if the element is present
     *
     * @param name Name of the element
     * @return Returns optional.of when present, empty() them missing
     */
    @NotNull
    Optional<T> childIfPresent(@NotNull String name);

    ResponsePresent<T> asPresent();
}
