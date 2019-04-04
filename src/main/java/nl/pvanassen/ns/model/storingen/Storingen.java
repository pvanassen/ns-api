package nl.pvanassen.ns.model.storingen;

import java.util.Collection;

import nl.pvanassen.ns.model.NsResult;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * http://www.ns.nl/reisinformatie/ns-api/documentatie-storingen-en-werkzaamheden.html
 * 
 * @author Paul van Assen
 * 
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Storingen implements NsResult {
    private final Collection<Storing> ongeplandeStoringen;
    private final Collection<Storing> geplandeStoringen;
}
