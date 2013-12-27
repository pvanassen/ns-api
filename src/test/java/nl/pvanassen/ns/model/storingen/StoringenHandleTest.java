package nl.pvanassen.ns.model.storingen;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;


public class StoringenHandleTest {

    @Test
    public void testStoringen() {
        StoringenHandle handle = new StoringenHandle();
        Storingen storingen = handle.getModel(getClass().getResourceAsStream("/storingen.xml"));
        assertEquals(2, storingen.getOngeplandeStoringen().size());
        Storing deurne = storingen.getOngeplandeStoringen().get(0);
        assertEquals("prio-49726", deurne.getId());
        assertEquals("Eindhoven-Deurne/Venlo", deurne.getTraject());
        assertEquals("defecte trein", deurne.getReden());
        assertEquals("Tussen Eindhoven en Deurne rijden minder Sprinters door een defecte trein.\nHoudt u rekening met een extra reistijd van 15 - 30 min.\nDe verstoring is naar verwachting rond 17:30 uur verholpen.\n", deurne.getBericht());
        // 2013-12-27T16:43:00+0100
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0100"));
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 27);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 43);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        assertEquals(calendar.getTime(), deurne.getDatum());
        assertNull(deurne.getAdvies());
        assertNull(deurne.getPeriode());
        assertEquals(0, storingen.getGeplandeStoringen().size());
    }

}
