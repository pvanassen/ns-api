package nl.pvanassen.ns.model.reisadvies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class ReisadviesHandleV1Test {

    @Test
    public void testActualReisadvies() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandleV1 handle = new ReisadviesHandleV1();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-actual.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(15, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertEquals(1, intercity.getAantalOverstappen());
        assertEquals(94, intercity.getGeplandeReisTijdMinuten());
        assertEquals(94, intercity.getActueleReisTijdMinuten());
        assertTrue(intercity.isOptimaal());
        assertEquals("2013-12-27T17:54", intercity.getGeplandeAankomstTijd().toString());
        assertEquals("2013-12-27T17:54", intercity.getActueleAankomstTijd().toString());
        assertNull(intercity.getAankomstVertraging());
        assertEquals("2013-12-27T16:20", intercity.getGeplandeVertrekTijd().toString());
        assertEquals("2013-12-27T16:20", intercity.getActueleVertrekTijd().toString());
        assertNotNull(intercity.getMeldingen());
        assertEquals(1, intercity.getMeldingen().size());
        Melding melding = intercity.getMeldingen().get(0);
        assertEquals("2012_gn_asn_25feb_4mrt", melding.getId());
        assertEquals("Let op, werk aan het spoor Assen - Groningen", melding.getText());
        Assert.assertFalse(melding.isErnstig());
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
        assertEquals("2013-12-27T16:20", utrecht.getTijd().toString());
        assertEquals(false, utrecht.isGewijzigdVertrekspoor());
    }

    @Test
    public void testExample1() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandleV1 handle = new ReisadviesHandleV1();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-example1.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertEquals(1, intercity.getAantalOverstappen());
        assertEquals(90, intercity.getGeplandeReisTijdMinuten());
        assertEquals(90, intercity.getActueleReisTijdMinuten());
        Assert.assertFalse(intercity.isOptimaal());
        assertEquals("2012-02-27T14:21", intercity.getGeplandeAankomstTijd().toString());
        assertEquals("2012-02-27T14:21", intercity.getActueleAankomstTijd().toString());
        assertNull(intercity.getAankomstVertraging());
        assertEquals("2012-02-27T12:51", intercity.getGeplandeVertrekTijd().toString());
        assertEquals("2012-02-27T12:51", intercity.getActueleVertrekTijd().toString());
        assertNotNull(intercity.getMeldingen());
        assertEquals(0, intercity.getMeldingen().size());
        assertEquals("NIET-OPTIMAAL", intercity.getStatus());
        assertNotNull(intercity.getReisDelen());
        assertEquals(2, intercity.getReisDelen().size());
        ReisDeel utrechtApeldoorn = intercity.getReisDelen().get(0);
        assertNull(utrechtApeldoorn.getGeplandeStoringId());
        assertNull(utrechtApeldoorn.getOngeplandeStoringId());
        assertNotNull(utrechtApeldoorn.getReisDetails());
        assertEquals(0, utrechtApeldoorn.getReisDetails().size());
        assertEquals("TRAIN", utrechtApeldoorn.getReisSoort());
        assertEquals(1743, utrechtApeldoorn.getRitNummer());
        assertEquals("VOLGENS-PLAN", utrechtApeldoorn.getStatus());
        assertEquals("NS", utrechtApeldoorn.getVervoerder());
        assertEquals("Intercity", utrechtApeldoorn.getVervoerType());
        assertNotNull(utrechtApeldoorn.getReisStops());
        assertEquals(3, utrechtApeldoorn.getReisStops().size());
        ReisStop utrecht = utrechtApeldoorn.getReisStops().get(0);
        assertEquals("Utrecht Centraal", utrecht.getNaam());
        assertEquals("11", utrecht.getSpoor());
        assertEquals("2012-02-27T12:51", utrecht.getTijd().toString());
        assertEquals(false, utrecht.isGewijzigdVertrekspoor());
    }

    @Test
    public void testOngeplandeStoring() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandleV1 handle = new ReisadviesHandleV1();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-ongeplandestoring.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertNotNull(intercity.getMeldingen());
        assertEquals(1, intercity.getMeldingen().size());
        Melding melding = intercity.getMeldingen().get(0);
        assertEquals("", melding.getId());
        assertEquals("Dit reisadvies vervalt", melding.getText());
        assertTrue(melding.isErnstig());
        assertEquals(0, intercity.getAantalOverstappen());
        assertEquals(32, intercity.getGeplandeReisTijdMinuten());
        assertEquals(32, intercity.getActueleReisTijdMinuten());
        Assert.assertFalse(intercity.isOptimaal());
        assertEquals("2012-03-02T13:28", intercity.getGeplandeAankomstTijd().toString());
        assertEquals("2012-03-02T13:28", intercity.getActueleAankomstTijd().toString());
        assertNull(intercity.getAankomstVertraging());
        assertEquals("2012-03-02T12:56", intercity.getGeplandeVertrekTijd().toString());
        assertEquals("2012-03-02T12:56", intercity.getActueleVertrekTijd().toString());
        assertEquals("NIET-MOGELIJK", intercity.getStatus());
        assertNotNull(intercity.getReisDelen());
        assertEquals(1, intercity.getReisDelen().size());
        ReisDeel maastrichtRoermond = intercity.getReisDelen().get(0);
        assertNull(maastrichtRoermond.getGeplandeStoringId());
        assertEquals("prio-24008", maastrichtRoermond.getOngeplandeStoringId());
        assertNotNull(maastrichtRoermond.getReisDetails());
        assertEquals(0, maastrichtRoermond.getReisDetails().size());
        assertEquals("TRAIN", maastrichtRoermond.getReisSoort());
        assertEquals(848, maastrichtRoermond.getRitNummer());
        assertEquals("GEANNULEERD", maastrichtRoermond.getStatus());
        assertEquals("NS", maastrichtRoermond.getVervoerder());
        assertEquals("Intercity", maastrichtRoermond.getVervoerType());
        assertNotNull(maastrichtRoermond.getReisStops());
        assertEquals(3, maastrichtRoermond.getReisStops().size());
        ReisStop maastricht = maastrichtRoermond.getReisStops().get(0);
        assertEquals("Maastricht", maastricht.getNaam());
        assertEquals("3", maastricht.getSpoor());
        assertEquals("2012-03-02T12:56", maastricht.getTijd().toString());
        assertEquals(false, maastricht.isGewijzigdVertrekspoor());
    }

    @Test
    public void testReisDetail() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandleV1 handle = new ReisadviesHandleV1();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-reisdetails.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertNotNull(intercity.getMeldingen());
        assertNull(intercity.getAankomstVertraging());
        ReisDeel reisDeel = intercity.getReisDelen().get(0);
        assertNotNull(reisDeel.getReisDetails());
        assertEquals(4, reisDeel.getReisDetails().size());
        assertEquals("Reserveren buitenl. aanbevolen", reisDeel.getReisDetails().get(0));
        assertEquals("Bar/Buffet", reisDeel.getReisDetails().get(1));
        assertEquals("Rolstoel plaatsen", reisDeel.getReisDetails().get(2));
        assertEquals("Geen fietsen tot 1 april 2012", reisDeel.getReisDetails().get(3));
    }

    @Test
    public void testVertraging() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandleV1 handle = new ReisadviesHandleV1();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-vertraging.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertEquals("+17 min", intercity.getAankomstVertraging());
        Assert.assertFalse(intercity.isOptimaal());
        assertEquals("2010-09-14T15:48", intercity.getGeplandeAankomstTijd().toString());
        assertEquals("2010-09-14T16:05", intercity.getActueleAankomstTijd().toString());
        assertEquals("2010-09-14T15:17", intercity.getGeplandeVertrekTijd().toString());
        assertEquals("2010-09-14T15:17", intercity.getActueleVertrekTijd().toString());
    }

    @Test
    public void testWerkzaamheden() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ReisadviesHandleV1 handle = new ReisadviesHandleV1();
        List<ReisMogelijkheid> mogelijkheden = handle.getModel(getClass().getResourceAsStream(
                "/reisadvies/reisadvies-werkzaamheden.xml"));
        assertNotNull(mogelijkheden);
        assertEquals(1, mogelijkheden.size());
        ReisMogelijkheid intercity = mogelijkheden.get(0);
        assertNotNull(intercity.getMeldingen());
        ReisDeel reisDeel = intercity.getReisDelen().get(0);
        assertNotNull(reisDeel.getReisDetails());

        ReisDeel assenGroningen = intercity.getReisDelen().get(1);
        assertEquals("2012_gn_asn_25feb_4mrt", assenGroningen.getGeplandeStoringId());
        assertNotNull(assenGroningen.getReisDetails());
        assertEquals(1, assenGroningen.getReisDetails().size());
        assertEquals("Fiets meenemen niet mogelijk", assenGroningen.getReisDetails().get(0));
        assertEquals("TRAIN", assenGroningen.getReisSoort());
        assertEquals(0, assenGroningen.getRitNummer());
        assertEquals("VOLGENS-PLAN", assenGroningen.getStatus());
        assertEquals("NS", assenGroningen.getVervoerder());
        assertEquals("Snelbus i.p.v. Trein", assenGroningen.getVervoerType());
        assertNotNull(assenGroningen.getReisStops());
        assertEquals(2, assenGroningen.getReisStops().size());
    }
}
