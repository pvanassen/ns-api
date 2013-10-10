package nl.pvanassen.ns.model.vertrektijden;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden
 * 
 * @author Paul van Assen
 * 
 */
public class ActueleVertrekTijden {
	private final List<VertrekkendeTrein> vertrekkendeTreinen = new LinkedList<VertrekkendeTrein>();

	public List<VertrekkendeTrein> getVertrekkendeTreinen() {
		return vertrekkendeTreinen;
	}

}
