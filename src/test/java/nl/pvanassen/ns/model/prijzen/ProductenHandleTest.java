package nl.pvanassen.ns.model.prijzen;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductenHandleTest {

    @Test
    public void testGetModel() {
        ProductenHandle handle = new ProductenHandle();
        Producten producten = handle.getModel(getClass().getResourceAsStream("/prijzen.xml"));
        assertNotNull(producten);
        assertNotNull(producten.getProducten());
        assertEquals(2, producten.getProducten().size());
        assertEquals(10, producten.getTariefEenheden());
        Product enkeleReis = producten.getProducten().get(0);
        assertNotNull(enkeleReis);
        assertEquals("Enkele reis", enkeleReis.getNaam());
        assertEquals(6, enkeleReis.getPrijzen().size());
        Prijs volTarief = enkeleReis.getPrijzen().get(0);
        assertEquals("vol tarief", volTarief.getKorting());
        assertEquals(2, volTarief.getKlasse());
        assertEquals(240, volTarief.getPrijs());
    }

}
