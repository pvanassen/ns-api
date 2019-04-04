package nl.pvanassen.ns.model.reisadvies;

import java.io.Serializable;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * See <a href="http://www.ns.nl/api/api#api-documentatie-reisadviezen">documentatie reisadviezen</a> for the exact
 * meaning of all fields
 * 
 * @author Paul van Assen
 * 
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReisDeel implements Serializable {

    @NotNull
    private final String reisSoort;

    @NotNull
    private final String vervoerder;

    @NotNull
    private final String vervoerType;

    private final int ritNummer;

    @NotNull
    private final String status;

    @NotNull
    private final List<ReisStop> reisStops;

    @Nullable
    private final String ongeplandeStoringId;

    @Nullable
    private final String geplandeStoringId;

    @NotNull
    private final List<@NotNull String> reisDetails;
}
