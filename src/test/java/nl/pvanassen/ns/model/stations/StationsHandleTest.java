package nl.pvanassen.ns.model.stations;

import static org.junit.Assert.*;
import nl.pvanassen.ns.model.stations.Station;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.stations.StationsHandle;

import org.junit.Test;

public class StationsHandleTest {

    @Test
    public void testGetModel() {
        StationsHandle handle = new StationsHandle();
        Stations stations = handle.getModel(getClass().getResourceAsStream("/stations.xml"));
        assertNotNull(stations);
        assertNotNull(stations.getStations());
        assertNotEquals(0, stations.getStations().size());
        Station stationDenBosch = stations.getStations().get(0);
        assertNotNull(stationDenBosch.getNamen());
        assertEquals("H'bosch", stationDenBosch.getNamen().getKort());
        assertEquals("'s-Hertogenbosch", stationDenBosch.getNamen().getMiddel());
        assertEquals("'s-Hertogenbosch", stationDenBosch.getNamen().getLang());
        assertEquals("NL", stationDenBosch.getLand());
        assertEquals(8400319, stationDenBosch.getUicCode());
        assertEquals(51.69048d, stationDenBosch.getLat(), Double.MIN_VALUE);
        assertEquals(5.29362d, stationDenBosch.getLon(), Double.MIN_VALUE);
        assertEquals(2, stationDenBosch.getSynoniemen().size());
        assertEquals("Hertogenbosch ('s)", stationDenBosch.getSynoniemen().get(0));
        assertEquals("Den Bosch", stationDenBosch.getSynoniemen().get(1));
    }

}
