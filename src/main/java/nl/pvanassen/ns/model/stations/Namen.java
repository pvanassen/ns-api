package nl.pvanassen.ns.model.stations;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
public class Namen {
	private final String kort;
	private final String middel;
	private final String lang;

	public Namen(String kort, String middel, String lang) {
		super();
		this.kort = kort;
		this.middel = middel;
		this.lang = lang;
	}

	public String getKort() {
		return kort;
	}

	public String getMiddel() {
		return middel;
	}

	public String getLang() {
		return lang;
	}

}
