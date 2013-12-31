package nl.pvanassen.ns.model.storingen;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class StoringenHandleTest {

    @Test
    public void testGetModel() {
        StoringenHandle handle = new StoringenHandle();
        Storingen storingen = handle.getModel(getClass().getResourceAsStream("/storingen.xml"));
        Assert.assertEquals(2, storingen.getOngeplandeStoringen().size());
        Storing deurne = storingen.getOngeplandeStoringen().get(0);
        Assert.assertEquals("prio-49726", deurne.getId());
        Assert.assertEquals("Eindhoven-Deurne/Venlo", deurne.getTraject());
        Assert.assertEquals("defecte trein", deurne.getReden());
        Assert.assertEquals(
                "Tussen Eindhoven en Deurne rijden minder Sprinters door een defecte trein.\nHoudt u rekening met een extra reistijd van 15 - 30 min.\nDe verstoring is naar verwachting rond 17:30 uur verholpen.\n",
                deurne.getBericht());
        // 2013-12-27T16:43:00+0100
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0100"));
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 27);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 43);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(calendar.getTime(), deurne.getDatum());
        Assert.assertNull(deurne.getAdvies());
        Assert.assertNull(deurne.getPeriode());
        Assert.assertEquals(0, storingen.getGeplandeStoringen().size());
    }

}
