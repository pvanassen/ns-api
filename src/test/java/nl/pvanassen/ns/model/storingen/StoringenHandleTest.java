package nl.pvanassen.ns.model.storingen;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class StoringenHandleTest {

    @Test
    public void testActualStoringen() {
        StoringenHandle handle = new StoringenHandle();
        Storingen storingen = handle.getModel(getClass().getResourceAsStream("/storingen/storingen-actual.xml"));
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

    @Test
    public void testExampleStoringen() {
        StoringenHandle handle = new StoringenHandle();
        Storingen storingen = handle.getModel(getClass().getResourceAsStream("/storingen/storingen-example1.xml"));
        Assert.assertEquals(1, storingen.getOngeplandeStoringen().size());
        Storing denboschNijmegen = storingen.getOngeplandeStoringen().get(0);
        Assert.assertEquals("prio-13345", denboschNijmegen.getId());
        Assert.assertEquals("'s-Hertogenbosch-Nijmegen", denboschNijmegen.getTraject());
        Assert.assertEquals("beperkingen op last van de politie", denboschNijmegen.getReden());
        Assert.assertEquals("", denboschNijmegen.getBericht());
        // 2013-12-27T16:43:00+0100
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+0100"));
        calendar.set(Calendar.YEAR, 2010);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 16);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 16);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(calendar.getTime(), denboschNijmegen.getDatum());
        Assert.assertNull(denboschNijmegen.getAdvies());
        Assert.assertNull(denboschNijmegen.getPeriode());
        Assert.assertEquals(1, storingen.getGeplandeStoringen().size());
        Storing almereOostvaarders = storingen.getGeplandeStoringen().get(0);
        Assert.assertEquals("2010_almo_wp_18_19dec", almereOostvaarders.getId());
        Assert.assertEquals("Almere Oostvaarders-Weesp/Naarden-Bussum", almereOostvaarders.getTraject());
        Assert.assertEquals("Beperkt treinverkeer, businzet en/of omreizen, extra reistijd 15-30 min.",
                almereOostvaarders.getReden());
        Assert.assertEquals("", almereOostvaarders.getBericht());
        Assert.assertNull(almereOostvaarders.getDatum());
        Assert.assertEquals(
                "Maak gebruik van de overige treinen of de bussen:                 reis tussen Weesp en Almere Centrum met de NS-bus in                 plaats van de trein tussen Almere Centrum en Lelystad                 Centrum rijden vier Sprinters per uur reis tussen Almere                 Muziekwijk en Naarden-Bussum via Weesp",
                almereOostvaarders.getAdvies());
        Assert.assertEquals("zaterdag 18 en zondag 19 december", almereOostvaarders.getPeriode());
    }

}
