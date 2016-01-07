package nl.pvanassen.ns.model.stations;

import nl.pvanassen.ns.model.NsResultListWrapper;

import java.util.List;

/**
 * @author Paul van Assen
 */
public class Stations extends NsResultListWrapper<Station> {
    Stations(List<Station> stations) {
        super(stations);
    }
}
