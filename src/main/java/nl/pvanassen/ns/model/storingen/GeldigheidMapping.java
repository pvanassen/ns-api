package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.parse;

class GeldigheidMapping implements Mapping<Geldigheid> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    @NotNull
    @Override
    public Geldigheid map(@NotNull final ResponsePresent<JsonResponse> jsonResponse) {
        return Geldigheid.builder()
                .startDatum(parse(jsonResponse.requiredChild("startDatum").content(), FORMATTER))
                .eindDatum(parse(jsonResponse.requiredChild("eindDatum").content(), FORMATTER))
                .build();
    }
}
