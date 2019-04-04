package nl.pvanassen.ns.model.vertrektijden;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class ActueleVertrekTijdenHandleTest {

    @Test
    public void testActualVertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-actual.xml"));
        assertNotNull(vertrekkendeTreinen);
        assertEquals(15, vertrekkendeTreinen.size());
        VertrekkendeTrein sprinterBreda = vertrekkendeTreinen.get(0);
        assertEquals(0, sprinterBreda.getVertrekVertragingMinuten());
        assertNull(sprinterBreda.getVertrekVertraging());
        assertNull(sprinterBreda.getVertrekVertragingTekst());
        assertEquals("First train in the XML file departs to Breda", "Breda", sprinterBreda.getEindBestemming());
        assertEquals("Rit number should be 13677", 13677, sprinterBreda.getRitNummer());
        assertEquals("Expected a sprinter train", "Sprinter", sprinterBreda.getTreinSoort());
        assertNotNull(sprinterBreda.getOpmerkingen());
        assertEquals(0, sprinterBreda.getOpmerkingen().size());
        assertEquals(of(2013, 10, 10, 21, 45, 0),
                sprinterBreda.getVertrekTijd());
    }

    @Test
    public void testExampleVertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-example.xml"));
        assertNotNull(vertrekkendeTreinen);
        assertEquals(3, vertrekkendeTreinen.size());
        VertrekkendeTrein sprinterBreukelen = vertrekkendeTreinen.get(0);
        assertEquals(2, sprinterBreukelen.getVertrekVertragingMinuten());
        assertEquals("PT2M", sprinterBreukelen.getVertrekVertraging());
        assertEquals("+2 min", sprinterBreukelen.getVertrekVertragingTekst());
        assertEquals("First train in the XML file departs to Breukelen", "Breukelen",
                sprinterBreukelen.getEindBestemming());
        assertEquals("Rit number should be 7478", 7478, sprinterBreukelen.getRitNummer());
        assertEquals("Expected a sprinter train", "Sprinter", sprinterBreukelen.getTreinSoort());
        assertNotNull(sprinterBreukelen.getOpmerkingen());
        assertEquals(0, sprinterBreukelen.getOpmerkingen().size());
        assertEquals(of(2012, 2, 19, 22, 47, 0), sprinterBreukelen.getVertrekTijd());
    }

    @Test
    public void testOpmerkingen1Vertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-opmerkingen1.xml"));
        assertNotNull(vertrekkendeTreinen);
        assertEquals(1, vertrekkendeTreinen.size());
        VertrekkendeTrein intercityRotterdam = vertrekkendeTreinen.get(0);
        assertEquals(0, intercityRotterdam.getVertrekVertragingMinuten());
        assertNull(intercityRotterdam.getVertrekVertraging());
        assertNull(intercityRotterdam.getVertrekVertragingTekst());
        assertEquals("First train in the XML file departs to Rotterdam Centraal", "Rotterdam Centraal",
                intercityRotterdam.getEindBestemming());
        assertEquals("Rit number should be 669", 669, intercityRotterdam.getRitNummer());
        assertEquals("Expected an intercity train", "Intercity", intercityRotterdam.getTreinSoort());
        assertNotNull(intercityRotterdam.getOpmerkingen());
        assertEquals(1, intercityRotterdam.getOpmerkingen().size());
        assertEquals("Rijdt vandaag niet", intercityRotterdam.getOpmerkingen().get(0));
        assertEquals(of(2012, 2, 22, 17, 13, 0), intercityRotterdam.getVertrekTijd());
    }

    @Test
    public void testOpmerkingen2Vertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-opmerkingen2.xml"));
        assertNotNull(vertrekkendeTreinen);
        assertEquals(1, vertrekkendeTreinen.size());
        VertrekkendeTrein highspeedAmsterdam = vertrekkendeTreinen.get(0);
        assertEquals(1, highspeedAmsterdam.getVertrekVertragingMinuten());
        assertEquals("PT1M", highspeedAmsterdam.getVertrekVertraging());
        assertEquals("+1 min", highspeedAmsterdam.getVertrekVertragingTekst());
        assertEquals("First train in the XML file departs to Amsterdam Centraal", "Amsterdam Centraal",
                highspeedAmsterdam.getEindBestemming());
        assertEquals("Rit number should be 9339", 9339, highspeedAmsterdam.getRitNummer());
        assertEquals("Expected a Thalys train", "Thalys", highspeedAmsterdam.getTreinSoort());
        assertEquals("NS Hispeed", highspeedAmsterdam.getVervoerder());
        assertEquals("1-2", highspeedAmsterdam.getVertrekSpoor());
        assertNotNull(highspeedAmsterdam.getOpmerkingen());
        assertEquals(1, highspeedAmsterdam.getOpmerkingen().size());
        assertEquals("Niet instappen", highspeedAmsterdam.getOpmerkingen().get(0));
        assertEquals(of(2012, 2, 21, 15, 30, 0), highspeedAmsterdam.getVertrekTijd());
    }

    @Test
    public void testReistipVertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-reistip.xml"));
        assertNotNull(vertrekkendeTreinen);
        assertEquals(1, vertrekkendeTreinen.size());
        VertrekkendeTrein stoptreinTiel = vertrekkendeTreinen.get(0);
        assertEquals(0, stoptreinTiel.getVertrekVertragingMinuten());
        assertNull(stoptreinTiel.getVertrekVertraging());
        assertNull(stoptreinTiel.getVertrekVertragingTekst());
        assertEquals("First train in the XML file departs to Tiel", "Tiel", stoptreinTiel.getEindBestemming());
        assertEquals("Rit number should be 31156", 31156, stoptreinTiel.getRitNummer());
        assertEquals("Expected an stoptrein train", "Stoptrein", stoptreinTiel.getTreinSoort());
        assertEquals("Syntus", stoptreinTiel.getVervoerder());
        assertEquals("4", stoptreinTiel.getVertrekSpoor());
        assertNotNull(stoptreinTiel.getOpmerkingen());
        assertEquals(0, stoptreinTiel.getOpmerkingen().size());
        assertNotNull(stoptreinTiel.getReisTip());
        assertEquals("Stopt niet in Arnhem Zuid", stoptreinTiel.getReisTip());

        assertEquals(of(2012, 2, 22, 15, 32, 0), stoptreinTiel.getVertrekTijd());
    }
}
