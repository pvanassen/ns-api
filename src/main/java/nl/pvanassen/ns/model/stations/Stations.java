package nl.pvanassen.ns.model.stations;

import static java.util.Collections.unmodifiableList;

import java.util.List;

import nl.pvanassen.ns.model.NsResultListWrapper;

import lombok.Builder;

/**
 * @author Paul van Assen
 */
public class Stations extends NsResultListWrapper<Station> {

    @Builder
    private Stations(List<Station> stations) {
        super(unmodifiableList(stations));
    }
}
