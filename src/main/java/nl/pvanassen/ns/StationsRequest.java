package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Stations;

public class StationsRequest implements ApiRequest<Stations> {

    @Override
    public String getPath() {
        return "ns-api-stations-v2";
    }

    @Override
    public String getRequestString() {
        return "";
    }

    @Override
    public Class<Stations> getType() {
        return Stations.class;
    }

}
