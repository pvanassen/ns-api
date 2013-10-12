package nl.pvanassen.ns.model.vertrektijden;

import java.util.*;

/**
 * http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden
 * 
 * @author Paul van Assen
 * 
 */
public class ActueleVertrekTijden {
	private final List<VertrekkendeTrein> vertrekkendeTreinen;

	public ActueleVertrekTijden(List<VertrekkendeTrein> vertrekkendeTreinen) {
		this.vertrekkendeTreinen = Collections.unmodifiableList(vertrekkendeTreinen);
	}
	
	public List<VertrekkendeTrein> getVertrekkendeTreinen() {
		return vertrekkendeTreinen;
	}

}
