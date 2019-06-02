package nl.pvanassen.ns.model.storingen;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.pvanassen.ns.model.NsResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
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

    @Nullable
    private final String leafletUrl;

    @NotNull
    private final Reisadviezen reisadviezen;

    @NotNull
    private final List<@NotNull Geldigheid> geldigheidsLijst;

    @Nullable
    private final String verwachting;

    @NotNull
    private final String gevolg;

    @Nullable
    private final String gevolgType;

    @Nullable
    private final String fase;

    private final int impact;

    private final int maatschappij;

    @Nullable
    private final String alternatiefVervoer;

    private final boolean landelijk;

    @NotNull
    private final String oorzaak;

    @NotNull
    private final String header;

    @Nullable
    private final LocalDateTime meldtijd;

    @NotNull
    private final String periode;

    @NotNull
    private final VerstoringType type;

    @NotNull
    private final List<@NotNull Baanvak> baanvakken;

    @Nullable
    private final String versie;

    @Nullable
    private final String volgnummer;

    private final int prioriteit;
}
