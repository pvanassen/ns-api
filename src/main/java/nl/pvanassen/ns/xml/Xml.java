package nl.pvanassen.ns.xml;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

/**
 * Xml handling base class
 * 
 * @author Paul van Assen
 * 
 */
public interface Xml {

    /**
     * Get the XML root element
     * 
     * @param stream Stream to parse
     * @param rootName Root element name
     * @return Xml object
     */
    static Xml getXml(InputStream stream, String rootName) {
        return new XmlPresent(stream, rootName);
    }

    /**
     * @return Name of the element
     */
    @NotNull
    String name();

    /**
     * @return Content of the element
     */
    String content();

    /**
     * Get a child element
     * 
     * @param name Name of the child
     * @return Xml implementation, either present or absent
     */
    @NotNull
    Xml child(@NotNull String name);

    /**
     * Get all child elements by name
     * 
     * @param name Name of the child elements
     * @return A list of XML objects, either present or absent
     */
    @NotNull
    List<Xml> children(@NotNull String name);

    /**
     * Get the value of an attribute
     * 
     * @param name Name of the attribute to get
     * @return Value of the attribute if found, or an exception of not found
     */
    String attr(@NotNull String name);

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
    Optional<List<Xml>> childrenIfPresent(@NotNull String name);

    /**
     * Returns an optional if the element is present
     *
     * @param name Name of the element
     * @return Returns optional.of when present, empty() them missing
     */
    @NotNull
    Optional<Xml> childIfPresent(@NotNull String name);

}
