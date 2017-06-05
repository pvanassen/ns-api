package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Product element
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Product {
    private final String naam;
    private final List<Prijs> prijzen;
}
