package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Stations;

class StationsRequest extends ApiRequest<Stations> {

    /**
     * Limiting scope
     */
    StationsRequest() {
    }

    @Override
    String getPath() {
        return "ns-api-stations-v2";
    }

    @Override
    String getRequestString() {
        return "";
    }

    @Override
    Class<Stations> getType() {
        return Stations.class;
    }

}
