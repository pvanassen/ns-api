package nl.pvanassen.ns.xml;

import java.io.InputStream;
import java.util.List;

/**
 * Xml handling base class
 * 
 * @author Paul van Assen
 * 
 */
public abstract class Xml {

    /**
     * Get the XML root element
     * 
     * @param stream Stream to parse
     * @param rootName Root element name
     * @return Xml object
     */
    public static Xml getXml(InputStream stream, String rootName) {
        return new XmlPresent(stream, rootName);
    }

    /**
     * @return Name of the element
     */
    public abstract String name();

    /**
     * @return Content of the element
     */
    public abstract String content();

    /**
     * Get a child element
     * 
     * @param name Name of the child
     * @return Xml implementation, either present or absent
     */
    public abstract Xml child(String name);

    /**
     * Get all child elements by name
     * 
     * @param name Name of the child elements
     * @return A list of XML objects, either present or absent
     */
    public abstract List<Xml> children(String name);

    /**
     * Get the value of an attribute
     * 
     * @param name Name of the attribute to get
     * @return Value of the attribute if found, or an exception of not found
     */
    public abstract String attr(String name);

    /**
     * Checks to see if an element is present
     * 
     * @param name The name of the element to check
     * @return True if present
     */
    public abstract boolean isPresent(String name);

}
