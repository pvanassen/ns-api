package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

class BaanvakBeperingMapping implements Mapping<BaanvakBeperking> {

    @NotNull
    @Override
    public BaanvakBeperking map(@NotNull ResponsePresent<JsonResponse> jsonResponse) {
        String vanCode = jsonResponse.child("van").child("code").asPresent().content();
        String totCode = jsonResponse.child("naar").child("code").asPresent().content();
        List<String> viaCode = jsonResponse
                .child("via")
                .children("code")
                .stream()
                .map(Response::content)
                .collect(Collectors.toList());
        Richting richting = Richting.valueOf(jsonResponse.child("richting").content());
        return BaanvakBeperking.builder()
                .van(vanCode)
                .tot(totCode)
                .via(viaCode)
                .richting(richting)
                .build();
    }
}
