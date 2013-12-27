package nl.pvanassen.ns;

import nl.pvanassen.ns.model.stations.Stations;

public class StationsRequest extends AbstractApiRequest<Stations> {

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
