package nl.pvanassen.ns.handle;

import nl.pvanassen.ns.model.stations.Stations;

import org.junit.Assert;
import org.junit.Test;

public class StationsHandleTest {

    @Test
    public void testGetModel() {
        StationsHandle handle = new StationsHandle();
        Stations stations = handle.getModel(getClass().getResourceAsStream("/stations.xml"));
        Assert.assertNotNull(stations);
        Assert.assertNotNull(stations.getStations());
        Assert.assertNotEquals(0, stations.getStations().size());
    }

}
