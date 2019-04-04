package nl.pvanassen.ns.model.stations;

import java.util.List;

import nl.pvanassen.ns.model.NsResultListWrapper;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;

/**
 * @author Paul van Assen
 */
public class Stations extends NsResultListWrapper<Station> {

    @Builder
    private Stations(@NotNull final List<Station> stations) {
        super(stations);
    }
}
