package nl.pvanassen.ns.model.reisadvies;

import static org.junit.Assert.*;

import java.util.TimeZone;

import org.junit.Test;

public class ReisadviesHandleTest {

    @Test
    public void testGetModel() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        ReisMogelijkheden mogelijkheden = handle.getModel(getClass().getResourceAsStream("/reisadvies.xml"));
        assertNotNull(mogelijkheden);
        assertNotNull(mogelijkheden.getReisMogelijkheid());
        assertEquals(15, mogelijkheden.getReisMogelijkheid().size());
        ReisMogelijkheid intercity = mogelijkheden.getReisMogelijkheid().get(0);
        assertEquals(1, intercity.getAantalOverstappen());
        assertEquals(94, intercity.getGeplandeReisTijdMinuten());
        assertEquals(94, intercity.getActueleReisTijdMinuten());
        assertEquals("Fri Dec 27 16:54:00 GMT 2013", intercity.getGeplandeAankomstTijd().toString());
        assertEquals("Fri Dec 27 16:54:00 GMT 2013", intercity.getActueleAankomstTijd().toString());
        assertNull(intercity.getAankomstVertraging());
        assertEquals("Fri Dec 27 15:20:00 GMT 2013", intercity.getGeplandeVertrekTijd().toString());
        assertEquals("Fri Dec 27 15:20:00 GMT 2013", intercity.getActueleVertrekTijd().toString());
        assertNull(intercity.getMelding());
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
