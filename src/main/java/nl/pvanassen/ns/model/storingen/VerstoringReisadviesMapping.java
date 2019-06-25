package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;

import java.util.stream.Collectors;

class VerstoringReisadviesMapping {

    VerstoringReisadvies map(final JsonResponse response) {
        return VerstoringReisadvies.builder()
                .advies(response.children("advies")
                        .stream()
                        .map(Response::asPresent)
                        .map(Response::content)
                        .collect(Collectors.toList()))
                .build();
    }
}
