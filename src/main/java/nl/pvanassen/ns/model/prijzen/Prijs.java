package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Prijs element
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Prijs {
    private final String korting;
    private final int klasse;
    private final int prijs;
}
