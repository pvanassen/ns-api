package nl.pvanassen.ns.model.stations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Namen {
    private final String kort;
    private final String middel;
    private final String lang;
}
