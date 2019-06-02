package nl.pvanassen.ns.model.prijzen;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PrijsHandleV1Test {

    @Test
    public void testGetModel() {
        PrijsHandleV1 handle = new PrijsHandleV1();
        Prijzen prijzen = handle.getModel(getClass().getResourceAsStream("/prijzen/prijzen-example.xml"));
        assertNotNull(prijzen);
        assertNotNull(prijzen.getVervoerderKeuzes());
        assertEquals(3, prijzen.getVervoerderKeuzes().size());
        VervoerderKeuze vervoerderKeuze = prijzen.getVervoerderKeuzes().get("MEEST_NS");
        assertNotNull(vervoerderKeuze);
        assertEquals(82,vervoerderKeuze.getTariefEenheden());
        assertEquals("MEEST_NS", vervoerderKeuze.getNaam());
        assertEquals(2, vervoerderKeuze.getReisTypes().size());
        ReisType reisType = vervoerderKeuze.getReisTypes().get("Enkele reis");
        assertNotNull(reisType);
        assertEquals("Enkele reis", reisType.getNaam());
        assertEquals(2, reisType.getReisKlassen().size());
        ReisKlasse reisKlasse = reisType.getReisKlassen().get(2);
        assertNotNull(reisKlasse);
        assertEquals(2, reisKlasse.getKlasse());
        assertEquals(new BigDecimal("13.90"), reisKlasse.getTotaal());
        assertEquals(3, reisKlasse.getKorting().size());
        assertEquals(new BigDecimal("13.90"), reisKlasse.getKorting().get("vol tarief"));
        assertEquals(1, reisKlasse.getPrijsdeel().size());
        assertEquals("NS", reisKlasse.getPrijsdeel().get(0).getVervoerder());
        assertEquals("GDM", reisKlasse.getPrijsdeel().get(0).getNaar());
        assertEquals("RTD", reisKlasse.getPrijsdeel().get(0).getVan());
        assertEquals(new BigDecimal("13.90"),reisKlasse.getPrijsdeel().get(0).getPrijs());
    }

}
