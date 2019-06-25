package nl.pvanassen.ns.model.storingen;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.pvanassen.ns.model.NsResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Verstoring implements NsResult {

    @NotNull
    private final String id;

    @NotNull
    private final List<@NotNull BaanvakBeperking> baanvakBeperking;

    @NotNull
    private final String extraReistijd;

    @NotNull
    private final Reisadviezen reisadviezen;

    @NotNull
    private final List<@NotNull Geldigheid> geldigheidsLijst;

    @NotNull
    private final String gevolg;

    private final int impact;

    private final int maatschappij;

    private final boolean landelijk;

    @NotNull
    private final String oorzaak;

    @NotNull
    private final String header;

    @NotNull
    private final String periode;

    @NotNull
    private final VerstoringType type;

    @NotNull
    private final List<@NotNull Baanvak> baanvakken;

    @NotNull
    private final List<@NotNull Traject> trajecten;

    private final int prioriteit;
}
