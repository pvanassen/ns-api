package nl.pvanassen.ns.xml;

import java.io.InputStream;
import java.util.List;

public abstract class Xml {
	
	public static Xml getXml(InputStream stream, String rootName) {
		return new XmlPresent(stream, rootName);
	}
	
	public abstract String name();
	
	public abstract String content();
	
	public abstract Xml child(String name);
	
	public abstract List<Xml> children(String name);
	
	public abstract String string(String attributeName);
	
	public abstract int integer(String attributeName );
	
	public abstract boolean isPresent(String elementName);

}
