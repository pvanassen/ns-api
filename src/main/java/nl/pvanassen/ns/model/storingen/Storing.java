package nl.pvanassen.ns.model.storingen;

import java.util.Date;

/**
 * http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden
 * 
 * @author Paul van Assen
 * 
 */
public class Storing {
	private String id;
	private String traject;
	private String periode;
	private String reden;
	private String advies;
	private String bericht;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTraject() {
		return traject;
	}

	public void setTraject(String traject) {
		this.traject = traject;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getReden() {
		return reden;
	}

	public void setReden(String reden) {
		this.reden = reden;
	}

	public String getAdvies() {
		return advies;
	}

	public void setAdvies(String advies) {
		this.advies = advies;
	}

	public String getBericht() {
		return bericht;
	}

	public void setBericht(String bericht) {
		this.bericht = bericht;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	private Date datum;

}
