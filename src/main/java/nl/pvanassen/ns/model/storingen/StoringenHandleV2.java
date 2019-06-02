package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;

public class StoringenHandleV2 implements Handle<Verstoringen> {

    @NotNull
    @Override
    public Verstoringen getModel(@NotNull final InputStream stream) {
        final JsonResponse response = Response.getJson(stream);
        final List<Verstoring> result = response.children("payload")
                .stream()
                .map(jsonResponse -> jsonResponse.child("verstoring"))
                .map(this::map)
                .collect(Collectors.toList());
        return new Verstoringen(result);
    }

    private Verstoring map(final JsonResponse jsonResponse) {
        return Verstoring.builder()
                .id(jsonResponse.child("id").asPresent().content())
                .alternatiefVervoer(jsonResponse.child("alternatiefVervoer").content())
                .baanvakBeperking(emptyList()) // TODO: Create mapping
                .baanvakken(emptyList()) // TODO: Create mapping
                .extraReistijd(jsonResponse.child("extraReistijd").asPresent().content())
                .fase(jsonResponse.child("fase").content())
                .geldigheidsLijst(emptyList()) // TODO: Create mapping
                .gevolg(jsonResponse.child("gevolg").asPresent().content())
                .gevolgType(jsonResponse.child("gevolgType").content())
                .header(jsonResponse.child("header").asPresent().content())
                .impact(parseInt(jsonResponse.child("impact").asPresent().content()))
                .landelijk(parseBoolean(jsonResponse.child("landelijk").asPresent().content()))
                .leafletUrl(jsonResponse.child("leafletUrl").content())
                .maatschappij(parseInt(jsonResponse.child("maatschappij").asPresent().content()))
                .meldtijd(jsonResponse.childIfPresent("meldTijd")
                        .map(Response::content)
                        .map(LocalDateTime::parse)
                        .orElse(null))
                .oorzaak(jsonResponse.child("oorzaak").asPresent().content())
                .periode(jsonResponse.child("periode").asPresent().content())
                .prioriteit(parseInt(jsonResponse.child("prioriteit").asPresent().content()))
                .reisadviezen(mapReisAdviezn(jsonResponse.child("reisadviezen").asPresent()))
                .type(VerstoringType.valueOf(jsonResponse.child("type").asPresent().content()))
                .versie(jsonResponse.child("versie").content())
                .verwachting(jsonResponse.child("verwachting").content())
                .volgnummer(jsonResponse.child("volgnummer").content())
                .build();
    }

    @NotNull
    private Reisadviezen mapReisAdviezn(@NotNull final ResponsePresent<JsonResponse> jsonResponse) {
        return Reisadviezen.builder()
                .titel(jsonResponse.child("titel").asPresent().content())
                .verstoringreisadvies(emptyList())  // TODO: Create mapping
                .build();
    }

}
