package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Stations;

class StationsRequest extends ApiRequest<Stations> {

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
