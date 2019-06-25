package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

class VerstoringMapping implements Mapping<Verstoring> {

    private final BaanvakBeperingMapping baanvakBeperingMapping = new BaanvakBeperingMapping();

    private final BaanbakMapping baanbakMapping = new BaanbakMapping();

    private final GeldigheidMapping geldigheidMapping = new GeldigheidMapping();

    private final ReisadviezenMapping reisadviezenMapping = new ReisadviezenMapping();

    private final TrajectMapping trajectMapping = new TrajectMapping();

    @NotNull
    @Override
    public Verstoring map(@NotNull ResponsePresent<JsonResponse> jsonResponse) {
        return Verstoring.builder()
                .id(jsonResponse.child("id").asPresent().content())
                .baanvakBeperking(baanvakBeperingMapping.map(jsonResponse.children("baanvakBeperking")))
                .baanvakken(baanbakMapping.map(jsonResponse.children("baankakken")))
                .extraReistijd(jsonResponse.child("extraReistijd").asPresent().content())
                .geldigheidsLijst(geldigheidMapping.map(jsonResponse.children("geldigheidsLijst")))
                .gevolg(jsonResponse.child("gevolg").asPresent().content())
                .header(jsonResponse.child("header").asPresent().content())
                .impact(parseInt(jsonResponse.child("impact").asPresent().content()))
                .landelijk(parseBoolean(jsonResponse.child("landelijk").asPresent().content()))
                .maatschappij(parseInt(jsonResponse.child("maatschappij").asPresent().content()))
                .oorzaak(jsonResponse.child("oorzaak").asPresent().content())
                .periode(jsonResponse.child("periode").asPresent().content())
                .prioriteit(parseInt(jsonResponse.child("prioriteit").asPresent().content()))
                .reisadviezen(reisadviezenMapping.map(jsonResponse.child("reisadviezen").asPresent()))
                .type(VerstoringType.valueOf(jsonResponse.child("type").asPresent().content()))
                .trajecten(trajectMapping.map(jsonResponse.children("trajecten")))
                .build();
    }
}
