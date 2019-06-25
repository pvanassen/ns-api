package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class TrajectMapping implements Mapping<Traject> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    @NotNull
    @Override
    public Traject map(@NotNull ResponsePresent<JsonResponse> jsonResponse) {
        return Traject.builder()
                .stations(jsonResponse.children("stations").stream().map(JsonResponse::content).collect(Collectors.toList()))
                .beginTijd(LocalDateTime.parse(jsonResponse.child("begintijd").asPresent().content(), FORMATTER))
                .eindTijd(LocalDateTime.parse(jsonResponse.child("eindtijd").asPresent().content(), FORMATTER))
                .build();
    }
}
