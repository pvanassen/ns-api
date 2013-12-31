package nl.pvanassen.ns.model.reisadvies;

import static org.junit.Assert.*;

import java.util.List;
import java.util.TimeZone;

import org.junit.Test;

public class ReisadviesHandleTest {

    @Test
    public void testGetModel() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream("/reisadvies.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(15, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertEquals(1, intercity.getAantalOverstappen());
        assertEquals(94, intercity.getGeplandeReisTijdMinuten());
        assertEquals(94, intercity.getActueleReisTijdMinuten());
        assertEquals("Fri Dec 27 16:54:00 GMT 2013", intercity.getGeplandeAankomstTijd().toString());
        assertEquals("Fri Dec 27 16:54:00 GMT 2013", intercity.getActueleAankomstTijd().toString());
        assertNull(intercity.getAankomstVertraging());
        assertEquals("Fri Dec 27 15:20:00 GMT 2013", intercity.getGeplandeVertrekTijd().toString());
        assertEquals("Fri Dec 27 15:20:00 GMT 2013", intercity.getActueleVertrekTijd().toString());
        assertNotNull(intercity.getMeldingen());
        assertEquals(1, intercity.getMeldingen().size());
        Melding melding = intercity.getMeldingen().get(0);
        assertEquals("2012_gn_asn_25feb_4mrt", melding.getId());
        assertEquals("Let op, werk aan het spoor Assen - Groningen", melding.getText());
        assertFalse(melding.isErnstig());
        assertEquals("VOLGENS-PLAN", intercity.getStatus());
        assertNotNull(intercity.getReisDelen());
        assertEquals(2, intercity.getReisDelen().size());
        ReisDeel utrechtZwolle = intercity.getReisDelen().get(0);
        assertNull(utrechtZwolle.getGeplandeStoringId());
        assertNull(utrechtZwolle.getOngeplandeStoringId());
        assertNotNull(utrechtZwolle.getReisDetails());
        assertEquals(0, utrechtZwolle.getReisDetails().size());
        assertEquals("TRAIN", utrechtZwolle.getReisSoort());
        assertEquals(12557, utrechtZwolle.getRitNummer());
        assertEquals("VOLGENS-PLAN", utrechtZwolle.getStatus());
        assertEquals("NS", utrechtZwolle.getVervoerder());
        assertEquals("Intercity", utrechtZwolle.getVervoerType());
        assertNotNull(utrechtZwolle.getReisStops());
        assertEquals(3, utrechtZwolle.getReisStops().size());
        ReisStop utrecht = utrechtZwolle.getReisStops().get(0);
        assertEquals("Utrecht Centraal", utrecht.getNaam());
        assertEquals("11a", utrecht.getSpoor());
        assertEquals("Fri Dec 27 15:20:00 GMT 2013", utrecht.getTijd().toString());
        assertEquals(false, utrecht.isGewijzigdVertrekspoor());
    }

}
