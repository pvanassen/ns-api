package nl.pvanassen.ns;

import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTreinen;

class ActueleVertrekTijdenRequest extends ApiRequest<VertrekkendeTreinen> {

    private final String station;

    ActueleVertrekTijdenRequest(String station) {
        this.station = UrlParamHelper.encode(station);
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
