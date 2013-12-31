package nl.pvanassen.ns.model.prijzen;

import org.junit.Assert;
import org.junit.Test;

public class ProductenHandleTest {

    @Test
    public void testGetModel() {
        ProductenHandle handle = new ProductenHandle();
        Producten producten = handle.getModel(getClass().getResourceAsStream("/prijzen.xml"));
        Assert.assertNotNull(producten);
        Assert.assertNotNull(producten.getProducten());
        Assert.assertEquals(2, producten.getProducten().size());
        Assert.assertEquals(10, producten.getTariefEenheden());
        Product enkeleReis = producten.getProducten().get(0);
        Assert.assertNotNull(enkeleReis);
        Assert.assertEquals("Enkele reis", enkeleReis.getNaam());
        Assert.assertEquals(6, enkeleReis.getPrijzen().size());
        Prijs volTarief = enkeleReis.getPrijzen().get(0);
        Assert.assertEquals("vol tarief", volTarief.getKorting());
        Assert.assertEquals(2, volTarief.getKlasse());
        Assert.assertEquals(240, volTarief.getPrijs());
    }

}
