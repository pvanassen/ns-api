package nl.pvanassen.ns.model.stations;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
public class Namen {
	private String kort;
	private String middel;
	private String lang;

	public String getKort() {
		return kort;
	}

	public void setKort(String kort) {
		this.kort = kort;
	}

	public String getMiddel() {
		return middel;
	}

	public void setMiddel(String middel) {
		this.middel = middel;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}
