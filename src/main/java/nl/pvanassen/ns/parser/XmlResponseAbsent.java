package nl.pvanassen.ns.parser;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Missing XML element. Will return default values. This is used to ease traversing over a tree
 * 
 * @author Paul van Assen
 * 
 */
public class XmlResponseAbsent implements XmlResponse {

    private final String name;

    XmlResponseAbsent(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String name() {
        return name;
    }

    @Nullable
    @Override
    public String content() {
        return null;
    }

    @NotNull
    @Override
    public XmlResponse child(@NotNull final String name) {
        return new XmlResponseAbsent(name);
    }

    @NotNull
    @Override
    public List<XmlResponse> children(@NotNull final String name) {
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

    @Override
    public @NotNull Optional<XmlResponse> childIfPresent(@NotNull final String name) {
        return empty();
    }

    @Override
    public ResponsePresent<XmlResponse> asPresent() {
        throw new IllegalStateException("Element not present");
    }

    @Override
    public @NotNull ResponsePresent<XmlResponse> requiredChild(@NotNull final String name) {
        throw new IllegalStateException("Element not present");
    }
}
