package nl.pvanassen.ns;

import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

import java.util.List;

class ActueleVertrekTijdenRequest extends ApiRequest<List<VertrekkendeTrein>> {

    private final String station;

    ActueleVertrekTijdenRequest(String station) {
        this.station = station;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getPath()
     */
    @Override
    String getPath() {
        return "ns-api-avt";
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getRequestString()
     */
    @Override
    String getRequestString() {
        return "station=" + station;
    }
}
