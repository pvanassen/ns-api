package nl.pvanassen.ns.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlAbsent extends Xml {
	private final String name;

	public XmlAbsent(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String content() {
		return null;
	}

	@Override
	public Xml child(String name) {
		return new XmlAbsent(name);
	}

	@Override
	public List<Xml> children(String name) {
		return new ArrayList<Xml>();
	}

	@Override
	public String string(String attributeName) {
		return null;
	}

	@Override
	public int integer(String attributeName) {
		return 0;
	}

	@Override
	public boolean isPresent(String elementName) {
		return false;
	}

}
