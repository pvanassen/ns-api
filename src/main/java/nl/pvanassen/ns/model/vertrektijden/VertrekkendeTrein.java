package nl.pvanassen.ns.model.vertrektijden;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Departing train object. For an exact explaination of all fields, please see For more information see <a
 * href="http://www.ns.nl/reisinformatie/ns-api/documentatie-actuele-vertrektijden.html">documentatie actuele vertrektijden</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VertrekkendeTrein implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int ritNummer;

    @NotNull
    private final LocalDateTime vertrekTijd;

    @Nullable
    private final String vertrekVertraging;

    private final int vertrekVertragingMinuten;

    @Nullable
    private final String vertrekVertragingTekst;

    @NotNull
    private final String eindBestemming;

    @NotNull
    private final String treinSoort;

    @Nullable
    private final String routeTekst;

    @NotNull
    private final String vervoerder;

    @NotNull
    private final String vertrekSpoor;

    private final boolean gewijzigdVertrekspoor;

    @Nullable
    private final String reisTip;

    @NotNull
    private final List<@NotNull String> opmerkingen;
}
