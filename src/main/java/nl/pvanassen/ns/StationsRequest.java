package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Stations;

import org.jetbrains.annotations.NotNull;

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
    @NotNull
    @Override
    String getPath() {
        return "ns-api-stations-v2";
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getRequestString()
     */
    @NotNull
    @Override
    String getRequestString() {
        return "";
    }

}
