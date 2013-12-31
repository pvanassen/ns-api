package nl.pvanassen.ns.model.reisadvies;

import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class ReisadviesHandleTest {

    @Test
    public void testGetModel() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream("/reisadvies.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(15, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertEquals(1, intercity.getAantalOverstappen());
        Assert.assertEquals(94, intercity.getGeplandeReisTijdMinuten());
        Assert.assertEquals(94, intercity.getActueleReisTijdMinuten());
        Assert.assertEquals("Fri Dec 27 16:54:00 GMT 2013", intercity.getGeplandeAankomstTijd().toString());
        Assert.assertEquals("Fri Dec 27 16:54:00 GMT 2013", intercity.getActueleAankomstTijd().toString());
        Assert.assertNull(intercity.getAankomstVertraging());
        Assert.assertEquals("Fri Dec 27 15:20:00 GMT 2013", intercity.getGeplandeVertrekTijd().toString());
        Assert.assertEquals("Fri Dec 27 15:20:00 GMT 2013", intercity.getActueleVertrekTijd().toString());
        Assert.assertNotNull(intercity.getMeldingen());
        Assert.assertEquals(1, intercity.getMeldingen().size());
        Melding melding = intercity.getMeldingen().get(0);
        Assert.assertEquals("2012_gn_asn_25feb_4mrt", melding.getId());
        Assert.assertEquals("Let op, werk aan het spoor Assen - Groningen", melding.getText());
        Assert.assertFalse(melding.isErnstig());
        Assert.assertEquals("VOLGENS-PLAN", intercity.getStatus());
        Assert.assertNotNull(intercity.getReisDelen());
        Assert.assertEquals(2, intercity.getReisDelen().size());
        ReisDeel utrechtZwolle = intercity.getReisDelen().get(0);
        Assert.assertNull(utrechtZwolle.getGeplandeStoringId());
        Assert.assertNull(utrechtZwolle.getOngeplandeStoringId());
        Assert.assertNotNull(utrechtZwolle.getReisDetails());
        Assert.assertEquals(0, utrechtZwolle.getReisDetails().size());
        Assert.assertEquals("TRAIN", utrechtZwolle.getReisSoort());
        Assert.assertEquals(12557, utrechtZwolle.getRitNummer());
        Assert.assertEquals("VOLGENS-PLAN", utrechtZwolle.getStatus());
        Assert.assertEquals("NS", utrechtZwolle.getVervoerder());
        Assert.assertEquals("Intercity", utrechtZwolle.getVervoerType());
        Assert.assertNotNull(utrechtZwolle.getReisStops());
        Assert.assertEquals(3, utrechtZwolle.getReisStops().size());
        ReisStop utrecht = utrechtZwolle.getReisStops().get(0);
        Assert.assertEquals("Utrecht Centraal", utrecht.getNaam());
        Assert.assertEquals("11a", utrecht.getSpoor());
        Assert.assertEquals("Fri Dec 27 15:20:00 GMT 2013", utrecht.getTijd().toString());
        Assert.assertEquals(false, utrecht.isGewijzigdVertrekspoor());
    }

}
