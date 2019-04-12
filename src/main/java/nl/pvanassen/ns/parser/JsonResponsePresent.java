package nl.pvanassen.ns.parser;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import lombok.extern.slf4j.Slf4j;
import nl.pvanassen.ns.error.NsApiException;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
public class JsonResponsePresent implements JsonResponse, ResponsePresent<JsonResponse> {

    @NotNull
    private final String content;

    @NotNull
    private final String name;

    private final Map<String, List<JsonResponsePresent>> nameChildren = new HashMap<>();

    JsonResponsePresent(@NotNull final InputStream stream) {
        this("root", parse(stream));
    }

    private static JsonObject parse(final InputStream stream) {
        try {
            return JsonParser.object().from(stream);
        } catch (JsonParserException e) {
            throw new NsApiException("Cannot parse json", e);
        }
    }

    private void addNamedChild(final Integer name, final JsonResponsePresent jsonResponsePresent) {
        addNamedChild(name.toString(), jsonResponsePresent);
    }

    private void addNamedChild(final String name, final JsonResponsePresent jsonResponsePresent) {
        nameChildren.compute(name, (key, list) -> {
            if (list == null) {
                return new ArrayList<>(singletonList(jsonResponsePresent));
            }
            list.add(jsonResponsePresent);
            return list;
        });
    }

    private void addNamedChild(final String name, final List<JsonResponsePresent> jsonResponsePresents) {
        nameChildren.compute(name, (key, list) -> {
            if (list == null) {
                return new ArrayList<>(jsonResponsePresents);
            }
            list.addAll(jsonResponsePresents);
            return list;
        });
    }

    private JsonResponsePresent(@NotNull final String name, @NotNull final JsonObject jsonObject) {
        for (final Map.Entry<String, Object> entity : jsonObject.entrySet()) {
            if (entity.getValue() instanceof JsonObject) {
                addNamedChild(entity.getKey(), new JsonResponsePresent(entity.getKey(), (JsonObject)entity.getValue()));
            }
            else if (entity.getValue() instanceof JsonArray) {
                addNamedChild(entity.getKey(), getArray((JsonArray)entity.getValue()));
            }
            else {
                addNamedChild(entity.getKey(), new JsonResponsePresent(entity.getKey(), entity.getValue().toString()));
            }
        }
        this.content = jsonObject.toString();
        this.name = name;
    }

    private List<JsonResponsePresent> getArray(@NotNull JsonArray jsonObject) {
        return jsonObject.stream()
                .map(obj -> {
                    if (obj instanceof JsonObject) {
                        return singletonList(new JsonResponsePresent("", (JsonObject)obj));
                    }
                    else if (obj instanceof JsonArray) {
                        return getArray((JsonArray)obj);
                    }
                    else {
                        return singletonList(new JsonResponsePresent("", obj.toString()));
                    }
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private JsonResponsePresent(@NotNull final String name, @NotNull String content) {
        this.content = content;
        this.name = name;
    }

    @Override
    public @NotNull String content() {
        return content;
    }

    @Override
    public @NotNull String name() {
        return name;
    }

    @NotNull
    @Override
    public JsonResponse child(@NotNull final String name) {
        final List<JsonResponse> children = children(name);
        if (children.size() != 1) {
            log.debug("Could not find individual child node: {}", name);
            return new JsonResponseAbsent(name);
        }
        return children.get(0);
    }

    @Override
    public @NotNull ResponsePresent<JsonResponse> requiredChild(@NotNull final String name) {
        final List<JsonResponse> children = children(name);
        if (children.size() != 1) {
            throw new IllegalStateException("Element not present");
        }
        return children.get(0).asPresent();
    }

    @Override
    public @NotNull List<JsonResponse> children(@NotNull final String name) {
        final List<JsonResponsePresent> children = nameChildren.get(name);
        return children == null ? emptyList() : new ArrayList<>(children);
    }

    @Override
    public boolean isPresent(@NotNull final String name) {
        return nameChildren.containsKey(name);
    }

    @Override
    public @NotNull Optional<JsonResponse> childIfPresent(@NotNull final String name) {
        if (isPresent(name)) {
            return of(child(name));
        }
        return empty();
    }

    @Override
    public ResponsePresent<JsonResponse> asPresent() {
        return this;
    }
}
