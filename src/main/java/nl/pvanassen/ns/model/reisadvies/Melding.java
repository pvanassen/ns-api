package nl.pvanassen.ns.model.reisadvies;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Melding {
    private final String id;
    private final boolean ernstig;
    private final String text;
}
