package nl.pvanassen.ns.model.stations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * For the exact meaning of all the fields, see <a
 * href="http://www.ns.nl/reisinformatie/ns-api/documentatie-stationslijst.html">documentatie stationslijst</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Station implements Serializable {
    private final String code;
    private final String type;
    private final Namen namen;
    private final String land;
    private final int uicCode;
    private final double lat;
    private final double lon;
    private final List<String> synoniemen;
}