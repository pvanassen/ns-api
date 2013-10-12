package nl.pvanassen.ns.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import nl.pvanassen.ns.error.NsApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * From
 * http://blog.another-d-mention.ro/programming/the-simplest-way-to-parse-xml
 * -in-java/
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
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(stream);
			Element rootElement = document.getDocumentElement();
			if (!rootElement.getNodeName().equals(rootName))
				throw new NsApiException("Could not find root node: "
						+ rootName);
			return rootElement;
		} catch (Exception exception) {
			throw new NsApiException("Unknown error", exception);
		}
	}

	protected XmlPresent(InputStream stream, String rootName) {
		this(rootElement(stream, rootName));
	}

	private XmlPresent(Element element) {
		this.name = element.getNodeName();
		this.content = element.getTextContent();
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

	public String name() {
		return name;
	}

	public String content() {
		return content;
	}

	public Xml child(String name) {
		List<Xml> children = children(name);
		if (children.size() != 1) {
			logger.error("Could not find individual child node: " + name);
			return new XmlAbsent(name);
		}
		return children.get(0);
	}

	public List<Xml> children(String name) {
		List<Xml> children = nameChildren.get(name);
		return children == null ? new ArrayList<Xml>() : children;
	}

	public String string(String name) {
		String value = nameAttributes.get(name);
		if (value == null)
			throw new NsApiException("Could not find attribute: " + name
					+ ", in node: " + this.name);
		return value;
	}

	public int integer(String name) {
		return Integer.parseInt(string(name));
	}

	public boolean isPresent(String name) {
		return nameChildren.containsKey(name);
	}

}