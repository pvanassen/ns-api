package nl.pvanassen.ns.model;

import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapping<T> {

    @NotNull
    T map(@NotNull final ResponsePresent<JsonResponse> jsonResponse);

    @NotNull
    default List<T> map(@NotNull final List<JsonResponse> jsonResponses) {
        return jsonResponses.stream()
                .map(Response::asPresent)
                .map(this::map)
                .collect(Collectors.toList());
    }
}
