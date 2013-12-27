package nl.pvanassen.ns;

import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

class ActueleVertrekTijdenRequest extends ApiRequest<ActueleVertrekTijden> {

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

    @Override
    Class<ActueleVertrekTijden> getType() {
        return ActueleVertrekTijden.class;
    }

}
