package nl.pvanassen.ns.model.vertrektijden;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class ActueleVertrekTijdenHandleTest {

    @Test
    public void testActualVertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-actual.xml"));
        Assert.assertNotNull(vertrekkendeTreinen);
        Assert.assertEquals(15, vertrekkendeTreinen.size());
        VertrekkendeTrein sprinterBreda = vertrekkendeTreinen.get(0);
        Assert.assertEquals(0, sprinterBreda.getVertrekVertragingMinuten());
        Assert.assertNull(sprinterBreda.getVertrekVertraging());
        Assert.assertNull(sprinterBreda.getVertrekVertragingTekst());
        Assert.assertEquals("First train in the XML file departs to Breda", "Breda", sprinterBreda.getEindBestemming());
        Assert.assertEquals("Rit number should be 13677", 13677, sprinterBreda.getRitNummer());
        Assert.assertEquals("Expected a sprinter train", "Sprinter", sprinterBreda.getTreinSoort());
        Assert.assertNotNull(sprinterBreda.getOpmerkingen());
        Assert.assertEquals(0, sprinterBreda.getOpmerkingen().size());
        // 2013-10-10T21:45:00+0200 to calendar
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0200"));
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals("Expected depature on 2013-10-10T21:45:00+0200", calendar.getTime(),
                sprinterBreda.getVertrekTijd());
    }

    @Test
    public void testExampleVertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-example.xml"));
        Assert.assertNotNull(vertrekkendeTreinen);
        Assert.assertEquals(3, vertrekkendeTreinen.size());
        VertrekkendeTrein sprinterBreukelen = vertrekkendeTreinen.get(0);
        Assert.assertEquals(2, sprinterBreukelen.getVertrekVertragingMinuten());
        Assert.assertEquals("PT2M", sprinterBreukelen.getVertrekVertraging());
        Assert.assertEquals("+2 min", sprinterBreukelen.getVertrekVertragingTekst());
        Assert.assertEquals("First train in the XML file departs to Breukelen", "Breukelen",
                sprinterBreukelen.getEindBestemming());
        Assert.assertEquals("Rit number should be 7478", 7478, sprinterBreukelen.getRitNummer());
        Assert.assertEquals("Expected a sprinter train", "Sprinter", sprinterBreukelen.getTreinSoort());
        Assert.assertNotNull(sprinterBreukelen.getOpmerkingen());
        Assert.assertEquals(0, sprinterBreukelen.getOpmerkingen().size());
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0200"));
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 19);
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 47);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(calendar.getTime(), sprinterBreukelen.getVertrekTijd());
    }

    @Test
    public void testOpmerkingen1Vertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-opmerkingen1.xml"));
        Assert.assertNotNull(vertrekkendeTreinen);
        Assert.assertEquals(1, vertrekkendeTreinen.size());
        VertrekkendeTrein intercityRotterdam = vertrekkendeTreinen.get(0);
        Assert.assertEquals(0, intercityRotterdam.getVertrekVertragingMinuten());
        Assert.assertNull(intercityRotterdam.getVertrekVertraging());
        Assert.assertNull(intercityRotterdam.getVertrekVertragingTekst());
        Assert.assertEquals("First train in the XML file departs to Rotterdam Centraal", "Rotterdam Centraal",
                intercityRotterdam.getEindBestemming());
        Assert.assertEquals("Rit number should be 669", 669, intercityRotterdam.getRitNummer());
        Assert.assertEquals("Expected an intercity train", "Intercity", intercityRotterdam.getTreinSoort());
        Assert.assertNotNull(intercityRotterdam.getOpmerkingen());
        Assert.assertEquals(1, intercityRotterdam.getOpmerkingen().size());
        Assert.assertEquals("Rijdt vandaag niet", intercityRotterdam.getOpmerkingen().get(0));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0200"));
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 13);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(calendar.getTime(), intercityRotterdam.getVertrekTijd());
    }

    @Test
    public void testOpmerkingen2Vertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-opmerkingen2.xml"));
        Assert.assertNotNull(vertrekkendeTreinen);
        Assert.assertEquals(1, vertrekkendeTreinen.size());
        VertrekkendeTrein highspeedAmsterdam = vertrekkendeTreinen.get(0);
        Assert.assertEquals(1, highspeedAmsterdam.getVertrekVertragingMinuten());
        Assert.assertEquals("PT1M", highspeedAmsterdam.getVertrekVertraging());
        Assert.assertEquals("+1 min", highspeedAmsterdam.getVertrekVertragingTekst());
        Assert.assertEquals("First train in the XML file departs to Amsterdam Centraal", "Amsterdam Centraal",
                highspeedAmsterdam.getEindBestemming());
        Assert.assertEquals("Rit number should be 9339", 9339, highspeedAmsterdam.getRitNummer());
        Assert.assertEquals("Expected a Thalys train", "Thalys", highspeedAmsterdam.getTreinSoort());
        Assert.assertEquals("NS Hispeed", highspeedAmsterdam.getVervoerder());
        Assert.assertEquals("1-2", highspeedAmsterdam.getVertrekSpoor());
        Assert.assertNotNull(highspeedAmsterdam.getOpmerkingen());
        Assert.assertEquals(1, highspeedAmsterdam.getOpmerkingen().size());
        Assert.assertEquals("Niet instappen", highspeedAmsterdam.getOpmerkingen().get(0));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0200"));
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 21);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(calendar.getTime(), highspeedAmsterdam.getVertrekTijd());
    }

    @Test
    public void testReistipVertrektijden() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        List<VertrekkendeTrein> vertrekkendeTreinen = handle.getModel(getClass().getResourceAsStream(
                "/vertrektijden/actuelevertrektijden-reistip.xml"));
        Assert.assertNotNull(vertrekkendeTreinen);
        Assert.assertEquals(1, vertrekkendeTreinen.size());
        VertrekkendeTrein stoptreinTiel = vertrekkendeTreinen.get(0);
        Assert.assertEquals(0, stoptreinTiel.getVertrekVertragingMinuten());
        Assert.assertNull(stoptreinTiel.getVertrekVertraging());
        Assert.assertNull(stoptreinTiel.getVertrekVertragingTekst());
        Assert.assertEquals("First train in the XML file departs to Tiel", "Tiel", stoptreinTiel.getEindBestemming());
        Assert.assertEquals("Rit number should be 31156", 31156, stoptreinTiel.getRitNummer());
        Assert.assertEquals("Expected an stoptrein train", "Stoptrein", stoptreinTiel.getTreinSoort());
        Assert.assertEquals("Syntus", stoptreinTiel.getVervoerder());
        Assert.assertEquals("4", stoptreinTiel.getVertrekSpoor());
        Assert.assertNotNull(stoptreinTiel.getOpmerkingen());
        Assert.assertEquals(0, stoptreinTiel.getOpmerkingen().size());
        Assert.assertNotNull(stoptreinTiel.getReisTip());
        Assert.assertEquals("Stopt niet in Arnhem Zuid", stoptreinTiel.getReisTip());

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0200"));
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 32);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(calendar.getTime(), stoptreinTiel.getVertrekTijd());
    }
}
