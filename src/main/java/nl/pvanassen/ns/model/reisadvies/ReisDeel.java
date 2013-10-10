package nl.pvanassen.ns.model.reisadvies;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisDeel {
	private String reisSoort;
	private String vervoerder;
	private String vervoerType;
	private int ritNummer;
	private String status;
	private final List<ReisStop> reisStops = new LinkedList<ReisStop>();
	private String ongeplandeStoringId;
	private String geplandeStoringId;
	private final List<String> reisDetails = new LinkedList<String>();

	public String getReisSoort() {
		return reisSoort;
	}

	public String getOngeplandeStoringId() {
		return ongeplandeStoringId;
	}

	public void setOngeplandeStoringId(String ongeplandeStoringId) {
		this.ongeplandeStoringId = ongeplandeStoringId;
	}

	public String getGeplandeStoringId() {
		return geplandeStoringId;
	}

	public void setGeplandeStoringId(String geplandeStoringId) {
		this.geplandeStoringId = geplandeStoringId;
	}

	public void setReisSoort(String reisSoort) {
		this.reisSoort = reisSoort;
	}

	public String getVervoerder() {
		return vervoerder;
	}

	public void setVervoerder(String vervoerder) {
		this.vervoerder = vervoerder;
	}

	public String getVervoerType() {
		return vervoerType;
	}

	public void setVervoerType(String vervoerType) {
		this.vervoerType = vervoerType;
	}

	public int getRitNummer() {
		return ritNummer;
	}

	public void setRitNummer(int ritNummer) {
		this.ritNummer = ritNummer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ReisStop> getReisStops() {
		return reisStops;
	}

	public List<String> getReisDetails() {
		return reisDetails;
	}

}
