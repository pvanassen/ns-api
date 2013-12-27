package nl.pvanassen.ns.model.reisadvies;

import static org.junit.Assert.*;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.stations.StationsHandle;

import org.junit.Test;


public class ReisadviesHandleTest {

    @Test
    public void testGetModel() {
        ReisadviesHandle handle = new ReisadviesHandle();
        ReisMogelijkheden mogelijkheden = handle.getModel(getClass().getResourceAsStream("/reisadvies.xml"));
        assertNotNull(mogelijkheden);
        assertNotNull(mogelijkheden.getReisMogelijkheid());
        assertEquals(15, mogelijkheden.getReisMogelijkheid().size());
    }

}
