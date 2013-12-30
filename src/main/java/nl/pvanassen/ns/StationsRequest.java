package nl.pvanassen.ns;

import java.util.List;

import nl.pvanassen.ns.model.stations.Station;

class StationsRequest extends ApiRequest<List<Station>> {

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


}
