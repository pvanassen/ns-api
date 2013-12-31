package nl.pvanassen.ns.model.stations;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StationsHandleTest {

    @Test
    public void testGetModel() {
        StationsHandle handle = new StationsHandle();
        List<Station> stations = handle.getModel(getClass().getResourceAsStream("/stations/stations-actual.xml"));
        Assert.assertNotNull(stations);
        Assert.assertNotEquals(0, stations.size());
        Station stationDenBosch = stations.get(0);
        Assert.assertNotNull(stationDenBosch.getNamen());
        Assert.assertEquals("H'bosch", stationDenBosch.getNamen().getKort());
        Assert.assertEquals("'s-Hertogenbosch", stationDenBosch.getNamen().getMiddel());
        Assert.assertEquals("'s-Hertogenbosch", stationDenBosch.getNamen().getLang());
        Assert.assertEquals("NL", stationDenBosch.getLand());
        Assert.assertEquals(8400319, stationDenBosch.getUicCode());
        Assert.assertEquals(51.69048d, stationDenBosch.getLat(), Double.MIN_VALUE);
        Assert.assertEquals(5.29362d, stationDenBosch.getLon(), Double.MIN_VALUE);
        Assert.assertEquals(2, stationDenBosch.getSynoniemen().size());
        Assert.assertEquals("Hertogenbosch ('s)", stationDenBosch.getSynoniemen().get(0));
        Assert.assertEquals("Den Bosch", stationDenBosch.getSynoniemen().get(1));
    }

}
