package nl.pvanassen.ns.model.stations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Namen implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String kort;
    private final String middel;
    private final String lang;
}
