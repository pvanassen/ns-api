package nl.pvanassen.ns.model.vertrektijden;

import org.junit.Test;

import java.util.List;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ActueleVertrekTijdenHandleV2Test {

    @Test
    public void testActualVertrektijden() {
        ActueleVertrekTijdenHandleV2 handle = new ActueleVertrekTijdenHandleV2();
        List<Departure> departures = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/departures.json"));
        assertNotNull(departures);
        assertEquals(4, departures.size());
        Departure intercityLeidenCentraal = departures.get(0);
        assertEquals(0, intercityLeidenCentraal.getVertrekVertragingMinuten());
        assertNull(intercityLeidenCentraal.getVertrekVertraging());
        assertNull(intercityLeidenCentraal.getVertrekVertragingTekst());
        assertEquals("First train in the XML file departs to Breda", "Breda", intercityLeidenCentraal.getEindBestemming());
        assertEquals("Rit number should be 13677", 13677, intercityLeidenCentraal.getRitNummer());
        assertEquals("Expected a sprinter train", "Sprinter", intercityLeidenCentraal.getTreinSoort());
        assertNotNull(intercityLeidenCentraal.getOpmerkingen());
        assertEquals(0, intercityLeidenCentraal.getOpmerkingen().size());
        assertEquals(of(2013, 10, 10, 21, 45, 0),
                intercityLeidenCentraal.getVertrekTijd());
    }


}