package nl.pvanassen.ns.model.stations;

import java.util.Collections;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
public class Stations {

    private final List<Station> stations;

    Stations(List<Station> stations) {
        this.stations = Collections.unmodifiableList(stations);
    }

    public List<Station> getStations() {
        return stations;
    }
}
