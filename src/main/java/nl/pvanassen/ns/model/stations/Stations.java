package nl.pvanassen.ns.model.stations;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
public class Stations {
	private final List<Station> stations = new LinkedList<Station>();
	
	public List<Station> getStations() {
		return stations;
	}
}
