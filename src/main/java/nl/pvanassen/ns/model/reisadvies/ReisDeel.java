package nl.pvanassen.ns.model.reisadvies;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    private final String reisSoort;
    private final String vervoerder;
    private final String vervoerType;
    private final int ritNummer;
    private final String status;
    private final List<ReisStop> reisStops;
    private final String ongeplandeStoringId;
    private final String geplandeStoringId;
    private final List<String> reisDetails;
}
