package nl.pvanassen.ns.model.stations;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
public class Station {
	private String code;
	private String type;
	private Namen namen;
	private String land;
	private int uicCode;
	private double lat;
	private double lon;
	private List<String> synoniemen = new LinkedList<String>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Namen getNamen() {
		return namen;
	}

	public void setNamen(Namen namen) {
		this.namen = namen;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getUicCode() {
		return uicCode;
	}

	public void setUicCode(int uicCode) {
		this.uicCode = uicCode;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public List<String> getSynoniemen() {
		return synoniemen;
	}

	public void setSynoniemen(List<String> synoniemen) {
		this.synoniemen = synoniemen;
	}
}
