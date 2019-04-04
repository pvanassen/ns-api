package nl.pvanassen.ns.model.stations;

import static java.util.Collections.unmodifiableList;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

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
        final Xml xml = Xml.getXml(stream, "Stations");

        final List<Station> stations = xml.children("Station")
                .stream()
                .map(this::getStation)
                .collect(Collectors.toList());
        return Stations.builder().stations(stations).build();
    }

    private Station getStation(final Xml stationXml) {
        final String code = stationXml.child("Code").content();
        final String type = stationXml.child("Type").content();
        final Namen namen = Namen.builder()
                .kort(stationXml.child("Namen").child("Kort").content())
                .middel(stationXml.child("Namen").child("Middel").content())
                .lang(stationXml.child("Namen").child("Lang").content())
                .build();

        final String land = stationXml.child("Land").content();
        final int uicCode = Integer.parseInt(stationXml.child("UICCode").content());
        final double lat = Double.parseDouble(stationXml.child("Lat").content());
        final double lon = Double.parseDouble(stationXml.child("Lon").content());

        final List<String> synoniemen = stationXml.child("Synoniemen").children("Synoniem")
                .stream()
                .map(Xml::content)
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
