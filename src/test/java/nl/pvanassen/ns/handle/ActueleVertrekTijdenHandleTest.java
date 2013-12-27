package nl.pvanassen.ns.handle;

import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

import org.junit.Assert;
import org.junit.Test;

public class ActueleVertrekTijdenHandleTest {

    @Test
    public void testGetModel() {
        ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
        ActueleVertrekTijden actueleVertrekTijden = handle.getModel(getClass().getResourceAsStream(
                "/actuelevertrektijden.xml"));
        Assert.assertNotNull(actueleVertrekTijden);
        Assert.assertNotNull(actueleVertrekTijden.getVertrekkendeTreinen());
        Assert.assertNotEquals(0, actueleVertrekTijden.getVertrekkendeTreinen().size());
        Assert.assertEquals(15, actueleVertrekTijden.getVertrekkendeTreinen().size());

    }

}
