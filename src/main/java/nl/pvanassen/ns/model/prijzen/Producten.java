package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Producten element
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Producten {
    private final int tariefEenheden;
    private final List<Product> producten;
}
