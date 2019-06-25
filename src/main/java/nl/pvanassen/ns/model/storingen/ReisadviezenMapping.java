package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

class ReisadviezenMapping implements Mapping<Reisadviezen> {

    private final VerstoringReisadviesMapping verstoringReisadviesMapping = new VerstoringReisadviesMapping();

    @NotNull
    @Override
    public Reisadviezen map(@NotNull final ResponsePresent<JsonResponse> jsonResponse) {
        return Reisadviezen.builder()
                .titel(jsonResponse.child("titel").asPresent().content())
                .verstoringreisadvies(jsonResponse.children("reisadvies")
                        .stream()
                        .map(verstoringReisadviesMapping::map)
                        .collect(Collectors.toList()))
                .build();
    }

}
