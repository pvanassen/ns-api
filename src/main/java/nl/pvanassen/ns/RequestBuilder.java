package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;


public class RequestBuilder {
    // Only one instance possible. Pre-instantiating this
    private static final StationsRequest INSTANCE = new StationsRequest();

    public static ApiRequest<ActueleVertrekTijden> getActueleVertrektijden(String station) {
        return new ActueleVertrekTijdenRequest(station);
    }
    
    public static ApiRequest<Stations> getStations() {
        return INSTANCE;
    }
    
    /**
     * Builds a request to return all 'Actuele storingen'
     * 
     * @return All 'Actuele storingen' request
     */
    public static ApiRequest<Storingen> getActueleStoringenRequest() {
        return new StoringenEnWerkzaamhedenRequest(null, Boolean.TRUE, null);
    }

    /**
     * Builds a request that will return all 'Geplande werkzaamheden'

     * @return All 'Geplanden werkzaamheden' request
     */
    public static  ApiRequest<Storingen> getGeplandeWerkzaamhedenRequest() {
        return new StoringenEnWerkzaamhedenRequest(null, null, Boolean.TRUE);
    }
    
    /**
     * Builds a request that will return all 'Geplande werkzaamheden'

     * @return All 'Geplanden werkzaamheden' request
     */
    public static  ApiRequest<Storingen> getActueleStoringen(String station) {
        return new StoringenEnWerkzaamhedenRequest(station, null, null);
    }

}
