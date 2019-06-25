package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

class BaanbakMapping implements Mapping<Baanvak> {

    @NotNull
    @Override
    public Baanvak map(@NotNull ResponsePresent<JsonResponse> jsonResponse) {
        return Baanvak.builder()
                .stations(jsonResponse.children("station")
                        .stream()
                        .map(JsonResponse::asPresent)
                        .map(Response::content)
                        .collect(Collectors.toList()))
                .build();
    }
}
