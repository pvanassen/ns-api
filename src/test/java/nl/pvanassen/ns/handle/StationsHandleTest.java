package nl.pvanassen.ns.handle;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import nl.pvanassen.ns.model.stations.Stations;

import org.junit.Test;

public class StationsHandleTest {

	@Test
	public void testGetModel() {
		StationsHandle handle = new StationsHandle();
		Stations stations = handle.getModel(getClass().getResourceAsStream("/stations.xml"));
		assertNotNull(stations);
		assertNotNull(stations.getStations());
		assertNotEquals(0, stations.getStations().size());
	}

}
