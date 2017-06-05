package nl.pvanassen.ns.model.reisadvies;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ReisStop {
    private final String naam;
    private final Date tijd;
    private final String spoor;
    private final boolean gewijzigdVertrekspoor;
}
