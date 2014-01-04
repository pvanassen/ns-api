package nl.pvanassen.ns.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Missing XML element. Will return default values. This is used to ease traversing over a tree
 * 
 * @author Paul van Assen
 * 
 */
public class XmlAbsent extends Xml {

    private final String name;

    XmlAbsent(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#name()
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#content()
     */
    @Override
    public String content() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#child(java.lang.String)
     */
    @Override
    public Xml child(String name) {
        return new XmlAbsent(name);
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#children(java.lang.String)
     */
    @Override
    public List<Xml> children(String name) {
        return new ArrayList<Xml>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#attr(java.lang.String)
     */
    @Override
    public String attr(String attributeName) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#isPresent(java.lang.String)
     */
    @Override
    public boolean isPresent(String elementName) {
        return false;
    }

}
