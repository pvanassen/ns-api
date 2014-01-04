package nl.pvanassen.ns.xml;

import java.io.InputStream;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import nl.pvanassen.ns.error.NsApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;

/**
 * From <a href="http://blog.another-d-mention.ro/programming/the-simplest-way-to-parse-xml -in-java/">the simplest way
 * to parse XML</a>
 * 
 * @author Paul van Assen
 * 
 */
public class XmlPresent extends Xml {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String name;

    private final String content;

    private final Map<String, String> nameAttributes = new HashMap<String, String>();

    private final Map<String, List<Xml>> nameChildren = new HashMap<String, List<Xml>>();

    private static Element rootElement(InputStream stream, String rootName) {
        if (stream == null) {
            throw new NullPointerException("Inputstream cannot be null");
        }
        if (rootName == null) {
            throw new NullPointerException("Name of the root element cannot be null");
        }
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(stream);
            Element rootElement = document.getDocumentElement();
            if (!rootElement.getNodeName().equals(rootName)) {
                throw new NsApiException("Could not find root node: " + rootName);
            }
            return rootElement;
        }
        catch (Exception exception) {
            throw new NsApiException("Unknown error", exception);
        }
    }

    /**
     * Called by abstract xml class to instantiate root element
     * 
     * @param stream Stream to use
     * @param rootName Root name to use
     */
    protected XmlPresent(InputStream stream, String rootName) {
        this(XmlPresent.rootElement(stream, rootName));
    }

    private XmlPresent(Element element) {
        name = element.getNodeName();
        content = element.getTextContent();
        NamedNodeMap namedNodeMap = element.getAttributes();
        int n = namedNodeMap.getLength();
        for (int i = 0; i < n; i++) {
            Node node = namedNodeMap.item(i);
            String name = node.getNodeName();
            addAttribute(name, node.getNodeValue());
        }
        NodeList nodes = element.getChildNodes();
        n = nodes.getLength();
        for (int i = 0; i < n; i++) {
            Node node = nodes.item(i);
            int type = node.getNodeType();
            if (type == Node.ELEMENT_NODE) {
                addChild(node.getNodeName(), new XmlPresent((Element) node));
            }
        }
    }

    private void addAttribute(String name, String value) {
        nameAttributes.put(name, value);
    }

    private void addChild(String name, XmlPresent child) {
        List<Xml> children = nameChildren.get(name);
        if (children == null) {
            children = new ArrayList<Xml>();
            nameChildren.put(name, children);
        }
        children.add(child);
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
        return content;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#child(java.lang.String)
     */
    @Override
    public Xml child(String name) {
        List<Xml> children = children(name);
        if (children.size() != 1) {
            logger.info("Could not find individual child node: " + name);
            return new XmlAbsent(name);
        }
        return children.get(0);
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#children(java.lang.String)
     */
    @Override
    public List<Xml> children(String name) {
        List<Xml> children = nameChildren.get(name);
        return children == null ? new ArrayList<Xml>() : children;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#attr(java.lang.String)
     */
    @Override
    public String attr(String name) {
        return nameAttributes.get(name);
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.xml.Xml#isPresent(java.lang.String)
     */
    @Override
    public boolean isPresent(String name) {
        return nameChildren.containsKey(name);
    }

}