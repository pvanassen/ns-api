package nl.pvanassen.ns.model.vertrektijden;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden
 * 
 * @author Paul van Assen
 * 
 */
public class VertrekkendeTrein {
	private int ritNummer;
	private Date vertrekTijd;
	private String vertrekVertraging;
	private String vertrekVertragingTekst;
	private String eindBestemming;
	private String treinSoort;
	private String routeTekst;
	private String vervoerder;
	private String vertrekSpoor;
	private boolean gewijzigdVertrekspoor;
	private String reisTip;
	private final List<String> opmerkingen = new LinkedList<String>();

	public int getRitNummer() {
		return ritNummer;
	}

	public void setRitNummer(int ritNummer) {
		this.ritNummer = ritNummer;
	}

	public Date getVertrekTijd() {
		return vertrekTijd;
	}

	public void setVertrekTijd(Date vertrekTijd) {
		this.vertrekTijd = vertrekTijd;
	}

	public String getVertrekVertraging() {
		return vertrekVertraging;
	}

	public void setVertrekVertraging(String vertrekVertraging) {
		this.vertrekVertraging = vertrekVertraging;
	}

	public String getVertrekVertragingTekst() {
		return vertrekVertragingTekst;
	}

	public void setVertrekVertragingTekst(String vertrekVertragingTekst) {
		this.vertrekVertragingTekst = vertrekVertragingTekst;
	}

	public String getEindBestemming() {
		return eindBestemming;
	}

	public void setEindBestemming(String eindBestemming) {
		this.eindBestemming = eindBestemming;
	}

	public String getTreinSoort() {
		return treinSoort;
	}

	public void setTreinSoort(String treinSoort) {
		this.treinSoort = treinSoort;
	}

	public String getRouteTekst() {
		return routeTekst;
	}

	public void setRouteTekst(String routeTekst) {
		this.routeTekst = routeTekst;
	}

	public String getVervoerder() {
		return vervoerder;
	}

	public void setVervoerder(String vervoerder) {
		this.vervoerder = vervoerder;
	}

	public String getVertrekSpoor() {
		return vertrekSpoor;
	}

	public void setVertrekSpoor(String vertrekSpoor) {
		this.vertrekSpoor = vertrekSpoor;
	}

	public boolean isGewijzigdVertrekspoor() {
		return gewijzigdVertrekspoor;
	}

	public void setGewijzigdVertrekspoor(boolean gewijzigdVertrekspoor) {
		this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
	}

	public String getReisTip() {
		return reisTip;
	}

	public void setReisTip(String reisTip) {
		this.reisTip = reisTip;
	}

	public List<String> getOpmerkingen() {
		return opmerkingen;
	}
}
