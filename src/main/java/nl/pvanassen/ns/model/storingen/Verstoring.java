package nl.pvanassen.ns.model.storingen;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
public class Verstoring {

    @NotNull
    private final String id;

    @NotNull
    private final List<@NotNull BaanvakBeperking> baanvakBeperking;

    @NotNull
    private final String reden;

    @NotNull
    private final String extraReistijd;

    @NotNull
    private final String leafletUrl;

    @NotNull
    private final Reisadviezen reisadviezen;

    @NotNull
    private final List<@NotNull Geldigheid> geldigheidsLijst;

    @NotNull
    private final String verwachting;

    @NotNull
    private final String gevolg;

    @NotNull
    private final String gevolgType;

    @NotNull
    private final String fase;

    private final int impact;

    private final int maatschappij;

    @NotNull
    private final String alternatiefVervoer;

    private final boolean landelijk;

    @NotNull
    private final String oorzaak;

    @NotNull
    private final String header;

    @NotNull
    private final LocalDateTime meldtijd;

    @NotNull
    private final String periode;

    @NotNull
    private final VerstoringType type;

    @NotNull
    private final List<@NotNull Baanvak> baanvakken;

    @NotNull
    private final String versie;

    @NotNull
    private final String volgnummer;

    private final int prioriteit;
}
