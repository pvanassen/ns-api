package nl.pvanassen.ns.xml;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

/**
 * Missing XML element. Will return default values. This is used to ease traversing over a tree
 * 
 * @author Paul van Assen
 * 
 */
public class XmlAbsent implements Xml {

    private final String name;

    XmlAbsent(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String name() {
        return name;
    }

    @Override
    public String content() {
        return null;
    }

    @NotNull
    @Override
    public Xml child(@NotNull final String name) {
        return new XmlAbsent(name);
    }

    @NotNull
    @Override
    public List<Xml> children(@NotNull final String name) {
        return emptyList();
    }

    @Override
    public String attr(@NotNull final String attributeName) {
        return null;
    }

    @Override
    public boolean isPresent(@NotNull final String elementName) {
        return false;
    }

    @NotNull
    @Override
    public Optional<List<Xml>> childrenIfPresent(@NotNull final String name) {
        return empty();
    }

    @NotNull
    @Override
    public Optional<Xml> childIfPresent(@NotNull final String name) {
        return empty();
    }
}
