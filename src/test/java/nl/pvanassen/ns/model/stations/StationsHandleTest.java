package nl.pvanassen.ns.model.stations;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class StationsHandleTest {

    @Test
    public void testGetModel() {
        StationsHandle handle = new StationsHandle();
        List<Station> stations = handle.getModel(getClass().getResourceAsStream("/stations/stations-actual.xml"));
        assertNotNull(stations);
        assertNotEquals(0, stations.size());
        Station stationDenBosch = stations.get(0);
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
