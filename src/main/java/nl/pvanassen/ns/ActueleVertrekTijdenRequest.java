package nl.pvanassen.ns;

import java.util.List;

import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

class ActueleVertrekTijdenRequest extends ApiRequest<List<VertrekkendeTrein>> {

    private final String station;

    ActueleVertrekTijdenRequest(String station) {
        this.station = station;
    }

    @Override
    String getPath() {
        return "ns-api-avt";
    }

    @Override
    String getRequestString() {
        return "station=" + station;
    }
}
