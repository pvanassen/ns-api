package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Station;

import java.util.List;

class StationsRequest extends ApiRequest<List<Station>> {

    /**
     * Limiting scope
     */
    StationsRequest() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getPath()
     */
    @Override
    String getPath() {
        return "ns-api-stations-v2";
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getRequestString()
     */
    @Override
    String getRequestString() {
        return "";
    }

}
