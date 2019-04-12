package nl.pvanassen.ns.parser;

import lombok.extern.slf4j.Slf4j;
import nl.pvanassen.ns.error.NsApiException;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * From <a href="http://blog.another-d-mention.ro/programming/the-simplest-way-to-parse-xml-in-java/">the simplest way
 * to parse XML</a>
 * 
 * @author Paul van Assen
 * 
 */
@Slf4j
public class XmlResponsePresent implements XmlResponse, ResponsePresent<XmlResponse> {

    private final String name;

    private final String content;

    private final Map<String, String> nameAttributes = new HashMap<>();

    private final Map<String, List<XmlResponsePresent>> nameChildren = new HashMap<>();

    @NotNull
    private static Element rootElement(@NotNull final InputStream stream, @NotNull final String rootName) {
        try {
            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final Document document = builder.parse(stream);
            final Element rootElement = document.getDocumentElement();
            if (rootElement.getNodeName().equals("error")) {
                throw new NsApiException(rootElement.getTextContent().trim());
            }
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
    XmlResponsePresent(@NotNull final InputStream stream, @NotNull final String rootName) {
        this(XmlResponsePresent.rootElement(stream, rootName));
    }

    @NotNull
    private XmlResponsePresent(@NotNull Element element) {
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
                addChild(node.getNodeName(), new XmlResponsePresent((Element) node));
            }
        }
    }

    private void addAttribute(@NotNull final String name, @NotNull final String value) {
        nameAttributes.put(name, value);
    }

    private void addChild(@NotNull final String name, @NotNull final XmlResponsePresent child) {
        nameChildren.compute(name, (key, list) -> {
            if (list == null) {
                return new LinkedList<>(singletonList(child));
            }
            list.add(child);
            return list;
        });
    }

    @NotNull
    @Override
    public String name() {
        return name;
    }

    @NotNull
    @Override
    public String content() {
        return content;
    }

    @NotNull
    @Override
    public XmlResponse child(@NotNull final String name) {
        final List<XmlResponse> children = children(name);
        if (children.size() != 1) {
            log.debug("Could not find individual child node: {}", name);
            return new XmlResponseAbsent(name);
        }
        return children.get(0);
    }

    @NotNull
    @Override
    public List<XmlResponse> children(@NotNull final String name) {
        final List<XmlResponsePresent> children = nameChildren.get(name);
        return children == null ? emptyList() : new ArrayList<>(children);
    }

    @Override
    public String attr(@NotNull final String name) {
        return nameAttributes.get(name);
    }

    @Override
    public boolean isPresent(@NotNull final String name) {
        return nameChildren.containsKey(name);
    }

    @Override
    public @NotNull Optional<XmlResponse> childIfPresent(@NotNull final String name) {
        if (isPresent(name)) {
            return of(child(name));
        }
        return empty();
    }

    @Override
    public ResponsePresent<XmlResponse> asPresent() {
        return this;
    }

    @Override
    public @NotNull ResponsePresent<XmlResponse> requiredChild(@NotNull final String name) {
        final List<XmlResponse> children = children(name);
        if (children.size() != 1) {
            throw new IllegalStateException("Element not present");
        }
        return children.get(0).asPresent();
    }
}