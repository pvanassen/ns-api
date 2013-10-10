package nl.pvanassen.ns.model.reisadvies;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class Melding {
	private String id;
	private boolean ernstig;
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isErnstig() {
		return ernstig;
	}

	public void setErnstig(boolean ernstig) {
		this.ernstig = ernstig;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
