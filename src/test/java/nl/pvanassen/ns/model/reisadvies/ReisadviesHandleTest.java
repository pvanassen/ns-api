package nl.pvanassen.ns.model.reisadvies;

import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class ReisadviesHandleTest {

    @Test
    public void testActualReisadvies() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-actual.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(15, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertEquals(1, intercity.getAantalOverstappen());
        Assert.assertEquals(94, intercity.getGeplandeReisTijdMinuten());
        Assert.assertEquals(94, intercity.getActueleReisTijdMinuten());
        Assert.assertTrue(intercity.isOptimaal());
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

    @Test
    public void testExample1() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-example1.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertEquals(1, intercity.getAantalOverstappen());
        Assert.assertEquals(90, intercity.getGeplandeReisTijdMinuten());
        Assert.assertEquals(90, intercity.getActueleReisTijdMinuten());
        Assert.assertFalse(intercity.isOptimaal());
        Assert.assertEquals("Mon Feb 27 13:21:00 GMT 2012", intercity.getGeplandeAankomstTijd().toString());
        Assert.assertEquals("Mon Feb 27 13:21:00 GMT 2012", intercity.getActueleAankomstTijd().toString());
        Assert.assertNull(intercity.getAankomstVertraging());
        Assert.assertEquals("Mon Feb 27 11:51:00 GMT 2012", intercity.getGeplandeVertrekTijd().toString());
        Assert.assertEquals("Mon Feb 27 11:51:00 GMT 2012", intercity.getActueleVertrekTijd().toString());
        Assert.assertNotNull(intercity.getMeldingen());
        Assert.assertEquals(0, intercity.getMeldingen().size());
        Assert.assertEquals("NIET-OPTIMAAL", intercity.getStatus());
        Assert.assertNotNull(intercity.getReisDelen());
        Assert.assertEquals(2, intercity.getReisDelen().size());
        ReisDeel utrechtApeldoorn = intercity.getReisDelen().get(0);
        Assert.assertNull(utrechtApeldoorn.getGeplandeStoringId());
        Assert.assertNull(utrechtApeldoorn.getOngeplandeStoringId());
        Assert.assertNotNull(utrechtApeldoorn.getReisDetails());
        Assert.assertEquals(0, utrechtApeldoorn.getReisDetails().size());
        Assert.assertEquals("TRAIN", utrechtApeldoorn.getReisSoort());
        Assert.assertEquals(1743, utrechtApeldoorn.getRitNummer());
        Assert.assertEquals("VOLGENS-PLAN", utrechtApeldoorn.getStatus());
        Assert.assertEquals("NS", utrechtApeldoorn.getVervoerder());
        Assert.assertEquals("Intercity", utrechtApeldoorn.getVervoerType());
        Assert.assertNotNull(utrechtApeldoorn.getReisStops());
        Assert.assertEquals(3, utrechtApeldoorn.getReisStops().size());
        ReisStop utrecht = utrechtApeldoorn.getReisStops().get(0);
        Assert.assertEquals("Utrecht Centraal", utrecht.getNaam());
        Assert.assertEquals("11", utrecht.getSpoor());
        Assert.assertEquals("Mon Feb 27 11:51:00 GMT 2012", utrecht.getTijd().toString());
        Assert.assertEquals(false, utrecht.isGewijzigdVertrekspoor());
    }

    @Test
    public void testOngeplandeStoring() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-ongeplandestoring.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertNotNull(intercity.getMeldingen());
        Assert.assertEquals(1, intercity.getMeldingen().size());
        Melding melding = intercity.getMeldingen().get(0);
        Assert.assertEquals("", melding.getId());
        Assert.assertEquals("Dit reisadvies vervalt", melding.getText());
        Assert.assertTrue(melding.isErnstig());
        Assert.assertEquals(0, intercity.getAantalOverstappen());
        Assert.assertEquals(32, intercity.getGeplandeReisTijdMinuten());
        Assert.assertEquals(32, intercity.getActueleReisTijdMinuten());
        Assert.assertFalse(intercity.isOptimaal());
        Assert.assertEquals("Fri Mar 02 12:28:00 GMT 2012", intercity.getGeplandeAankomstTijd().toString());
        Assert.assertEquals("Fri Mar 02 12:28:00 GMT 2012", intercity.getActueleAankomstTijd().toString());
        Assert.assertNull(intercity.getAankomstVertraging());
        Assert.assertEquals("Fri Mar 02 11:56:00 GMT 2012", intercity.getGeplandeVertrekTijd().toString());
        Assert.assertEquals("Fri Mar 02 11:56:00 GMT 2012", intercity.getActueleVertrekTijd().toString());
        Assert.assertEquals("NIET-MOGELIJK", intercity.getStatus());
        Assert.assertNotNull(intercity.getReisDelen());
        Assert.assertEquals(1, intercity.getReisDelen().size());
        ReisDeel maastrichtRoermond = intercity.getReisDelen().get(0);
        Assert.assertNull(maastrichtRoermond.getGeplandeStoringId());
        Assert.assertEquals("prio-24008", maastrichtRoermond.getOngeplandeStoringId());
        Assert.assertNotNull(maastrichtRoermond.getReisDetails());
        Assert.assertEquals(0, maastrichtRoermond.getReisDetails().size());
        Assert.assertEquals("TRAIN", maastrichtRoermond.getReisSoort());
        Assert.assertEquals(848, maastrichtRoermond.getRitNummer());
        Assert.assertEquals("GEANNULEERD", maastrichtRoermond.getStatus());
        Assert.assertEquals("NS", maastrichtRoermond.getVervoerder());
        Assert.assertEquals("Intercity", maastrichtRoermond.getVervoerType());
        Assert.assertNotNull(maastrichtRoermond.getReisStops());
        Assert.assertEquals(3, maastrichtRoermond.getReisStops().size());
        ReisStop maastricht = maastrichtRoermond.getReisStops().get(0);
        Assert.assertEquals("Maastricht", maastricht.getNaam());
        Assert.assertEquals("3", maastricht.getSpoor());
        Assert.assertEquals("Fri Mar 02 11:56:00 GMT 2012", maastricht.getTijd().toString());
        Assert.assertEquals(false, maastricht.isGewijzigdVertrekspoor());
    }

    @Test
    public void testReisDetail() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-reisdetails.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertNotNull(intercity.getMeldingen());
        Assert.assertNull(intercity.getAankomstVertraging());
        ReisDeel reisDeel = intercity.getReisDelen().get(0);
        Assert.assertNotNull(reisDeel.getReisDetails());
        Assert.assertEquals(4, reisDeel.getReisDetails().size());
        Assert.assertEquals("Reserveren buitenl. aanbevolen", reisDeel.getReisDetails().get(0));
        Assert.assertEquals("Bar/Buffet", reisDeel.getReisDetails().get(1));
        Assert.assertEquals("Rolstoel plaatsen", reisDeel.getReisDetails().get(2));
        Assert.assertEquals("Geen fietsen tot 1 april 2012", reisDeel.getReisDetails().get(3));
    }

    @Test
    public void testVertraging() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-vertraging.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertEquals("+17 min", intercity.getAankomstVertraging());
        Assert.assertFalse(intercity.isOptimaal());
        Assert.assertEquals("Tue Sep 14 13:48:00 GMT 2010", intercity.getGeplandeAankomstTijd().toString());
        Assert.assertEquals("Tue Sep 14 14:05:00 GMT 2010", intercity.getActueleAankomstTijd().toString());
        Assert.assertEquals("Tue Sep 14 13:17:00 GMT 2010", intercity.getGeplandeVertrekTijd().toString());
        Assert.assertEquals("Tue Sep 14 13:17:00 GMT 2010", intercity.getActueleVertrekTijd().toString());
    }

    @Test
    public void testWerkzaamheden() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandle handle = new ReisadviesHandle();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-werkzaamheden.xml"));
        Assert.assertNotNull(mogelijkheden);
        Assert.assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        Assert.assertNotNull(intercity.getMeldingen());
        ReisDeel reisDeel = intercity.getReisDelen().get(0);
        Assert.assertNotNull(reisDeel.getReisDetails());

        ReisDeel assenGroningen = intercity.getReisDelen().get(1);
        Assert.assertEquals("2012_gn_asn_25feb_4mrt", assenGroningen.getGeplandeStoringId());
        Assert.assertNotNull(assenGroningen.getReisDetails());
        Assert.assertEquals(1, assenGroningen.getReisDetails().size());
        Assert.assertEquals("Fiets meenemen niet mogelijk", assenGroningen.getReisDetails().get(0));
        Assert.assertEquals("TRAIN", assenGroningen.getReisSoort());
        Assert.assertEquals(0, assenGroningen.getRitNummer());
        Assert.assertEquals("VOLGENS-PLAN", assenGroningen.getStatus());
        Assert.assertEquals("NS", assenGroningen.getVervoerder());
        Assert.assertEquals("Snelbus i.p.v. Trein", assenGroningen.getVervoerType());
        Assert.assertNotNull(assenGroningen.getReisStops());
        Assert.assertEquals(2, assenGroningen.getReisStops().size());
    }
}
