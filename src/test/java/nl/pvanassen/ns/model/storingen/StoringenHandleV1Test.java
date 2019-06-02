package nl.pvanassen.ns.model.storingen;

import org.junit.Test;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StoringenHandleV1Test {

    @Test
    public void testActualStoringen() {
        StoringenHandleV1 handle = new StoringenHandleV1();
        Storingen storingen = handle.getModel(getClass().getResourceAsStream("/storingen/storingen-actual.xml"));
        assertEquals(2, storingen.getOngeplandeStoringen().size());
        Storing deurne = storingen.getOngeplandeStoringen().get(0);
        assertEquals("prio-49726", deurne.getId());
        assertEquals("Eindhoven-Deurne/Venlo", deurne.getTraject());
        assertEquals("defecte trein", deurne.getReden());
        assertEquals(
                "Tussen Eindhoven en Deurne rijden minder Sprinters door een defecte trein.\nHoudt u rekening met een extra reistijd van 15 - 30 min.\nDe verstoring is naar verwachting rond 17:30 uur verholpen.\n",
                deurne.getBericht());
        assertEquals(of(2013, 12, 27, 16, 43, 0), deurne.getDatum());
        assertNull(deurne.getAdvies());
        assertNull(deurne.getPeriode());
        assertEquals(0, storingen.getGeplandeStoringen().size());
    }

    @Test
    public void testExampleStoringen() {
        StoringenHandleV1 handle = new StoringenHandleV1();
        Storingen storingen = handle.getModel(getClass().getResourceAsStream("/storingen/storingen-example1.xml"));
        assertEquals(1, storingen.getOngeplandeStoringen().size());
        Storing denboschNijmegen = storingen.getOngeplandeStoringen().get(0);
        assertEquals("prio-13345", denboschNijmegen.getId());
        assertEquals("'s-Hertogenbosch-Nijmegen", denboschNijmegen.getTraject());
        assertEquals("beperkingen op last van de politie", denboschNijmegen.getReden());
        assertEquals("", denboschNijmegen.getBericht());
        assertEquals(of(2010, 12, 16, 11, 16, 0), denboschNijmegen.getDatum());
        assertNull(denboschNijmegen.getAdvies());
        assertNull(denboschNijmegen.getPeriode());
        assertEquals(1, storingen.getGeplandeStoringen().size());
        Storing almereOostvaarders = storingen.getGeplandeStoringen().get(0);
        assertEquals("2010_almo_wp_18_19dec", almereOostvaarders.getId());
        assertEquals("Almere Oostvaarders-Weesp/Naarden-Bussum", almereOostvaarders.getTraject());
        assertEquals("Beperkt treinverkeer, businzet en/of omreizen, extra reistijd 15-30 min.",
                almereOostvaarders.getReden());
        assertEquals("", almereOostvaarders.getBericht());
        assertNull(almereOostvaarders.getDatum());
        assertEquals(
                "Maak gebruik van de overige treinen of de bussen:                 reis tussen Weesp en Almere Centrum met de NS-bus in                 plaats van de trein tussen Almere Centrum en Lelystad                 Centrum rijden vier Sprinters per uur reis tussen Almere                 Muziekwijk en Naarden-Bussum via Weesp",
                almereOostvaarders.getAdvies());
        assertEquals("zaterdag 18 en zondag 19 december", almereOostvaarders.getPeriode());
    }

}
