package nl.pvanassen.ns.model.stations;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Namen implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String kort;
    private final String middel;
    private final String lang;
}
