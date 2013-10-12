package nl.pvanassen.ns.handle;

import static org.junit.Assert.*;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

import org.junit.Test;

public class ActueleVertrekTijdenHandleTest {

	@Test
	public void testGetModel() {
		ActueleVertrekTijdenHandle handle = new ActueleVertrekTijdenHandle();
		ActueleVertrekTijden actueleVertrekTijden = handle.getModel(getClass().getResourceAsStream("/actuelevertrektijden.xml"));
		assertNotNull(actueleVertrekTijden);
		assertNotNull(actueleVertrekTijden.getVertrekkendeTreinen());
		assertNotEquals(0, actueleVertrekTijden.getVertrekkendeTreinen().size());
	}

}
