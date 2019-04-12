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
public class JsonResponseAbsent implements JsonResponse {

    private final String name;

    JsonResponseAbsent(String name) {
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
    public JsonResponse child(@NotNull final String name) {
        return new JsonResponseAbsent(name);
    }

    @NotNull
    @Override
    public List<JsonResponse> children(@NotNull final String name) {
        return emptyList();
    }

    @Override
    public boolean isPresent(@NotNull final String elementName) {
        return false;
    }

    @Override
    public @NotNull Optional<JsonResponse> childIfPresent(@NotNull final String name) {
        return empty();
    }

    @Override
    public ResponsePresent<JsonResponse> asPresent() {
        throw new IllegalStateException("Element not present");
    }

    @Override
    public @NotNull ResponsePresent<JsonResponse> requiredChild(@NotNull final String name) {
        throw new IllegalStateException("Element not present");
    }
}
