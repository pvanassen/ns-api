package nl.pvanassen.ns.model.stations;

import static java.util.Collections.unmodifiableList;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.parser.Response;
import nl.pvanassen.ns.parser.XmlResponse;

import org.jetbrains.annotations.NotNull;

/**
 * Handles parsing the response from the NS and de-serializes it into a list of stations
 *
 * @author Paul van Assen
 *
 */
public class StationsHandle implements Handle<Stations> {

    /**
     *
     * {@inheritDoc}
     *
     * @see nl.pvanassen.ns.handle.Handle#getModel(java.io.InputStream)
     */
    @NotNull
    @Override
    public Stations getModel(@NotNull final InputStream stream) {
        final XmlResponse response = Response.getXml(stream, "Stations");

        final List<Station> stations = response.children("Station")
                .stream()
                .map(this::getStation)
                .collect(Collectors.toList());
        return Stations.builder().stations(stations).build();
    }

    private Station getStation(final XmlResponse stationResponse) {
        final String code = stationResponse.requiredChild("Code").content();
        final String type = stationResponse.requiredChild("Type").content();
        final Namen namen = Namen.builder()
                .kort(stationResponse.child("Namen").requiredChild("Kort").content())
                .middel(stationResponse.child("Namen").requiredChild("Middel").content())
                .lang(stationResponse.child("Namen").requiredChild("Lang").content())
                .build();

        final String land = stationResponse.requiredChild("Land").content();
        final int uicCode = Integer.parseInt(stationResponse.requiredChild("UICCode").content());
        final double lat = Double.parseDouble(stationResponse.requiredChild("Lat").content());
        final double lon = Double.parseDouble(stationResponse.requiredChild("Lon").content());

        final List<String> synoniemen = stationResponse.child("Synoniemen").children("Synoniem")
                .stream()
                .map(Response::content)
                .collect(Collectors.toList());

        return Station.builder()
                .code(code)
                .type(type)
                .namen(namen)
                .land(land)
                .uicCode(uicCode)
                .lat(lat)
                .lon(lon)
                .synoniemen(unmodifiableList(synoniemen))
                .build();

    }
}
