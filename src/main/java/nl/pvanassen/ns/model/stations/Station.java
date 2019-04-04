package nl.pvanassen.ns.model.stations;

import java.io.Serializable;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * For the exact meaning of all the fields, see <a
 * href="http://www.ns.nl/reisinformatie/ns-api/documentatie-stationslijst.html">documentatie stationslijst</a>
 * 
 * @author Paul van Assen
 * 
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Station implements Serializable {

    @NotNull
    private final String code;

    @NotNull
    private final String type;

    @NotNull
    private final Namen namen;

    @NotNull
    private final String land;

    private final int uicCode;
    private final double lat;
    private final double lon;

    @NotNull
    private final List<String> synoniemen;
}