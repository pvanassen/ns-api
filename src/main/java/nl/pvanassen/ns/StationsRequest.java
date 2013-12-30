package nl.pvanassen.ns;

import java.util.List;

import nl.pvanassen.ns.model.stations.Station;

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
