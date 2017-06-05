package nl.pvanassen.ns.model.storingen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Storingen {
    private final List<Storing> ongeplandeStoringen;
    private final List<Storing> geplandeStoringen;
}
