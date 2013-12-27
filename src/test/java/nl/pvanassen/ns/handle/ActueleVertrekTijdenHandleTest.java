package nl.pvanassen.ns.handle;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.TimeZone;

import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijdenHandle;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

import org.junit.Test;

public class ActueleVertrekTijdenHandleTest {

    @Test
    public void testGetModel() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        ActueleVertrekTijden actueleVertrekTijden = handle.getModel(getClass().getResourceAsStream(
                "/actuelevertrektijden.xml"));
        assertNotNull(actueleVertrekTijden);
        assertNotNull(actueleVertrekTijden.getVertrekkendeTreinen());
        assertNotEquals(0, actueleVertrekTijden.getVertrekkendeTreinen().size());
        assertEquals(15, actueleVertrekTijden.getVertrekkendeTreinen().size());
        VertrekkendeTrein sprinterBreda = actueleVertrekTijden.getVertrekkendeTreinen().get(0);
        assertEquals("First train in the XML file departs to Breda", "Breda", sprinterBreda.getEindBestemming());
        assertEquals("Rit number should be 13677", 13677, sprinterBreda.getRitNummer());
        assertEquals("Expected a sprinter train", "Sprinter", sprinterBreda.getTreinSoort());
        // 2013-10-10T21:45:00+0200 to calendar
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0200"));
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        assertEquals("Expected depature on 2013-10-10T21:45:00+0200", calendar.getTime(),
                sprinterBreda.getVertrekTijd());
    }

}
