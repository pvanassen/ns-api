package nl.pvanassen.ns;

import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

public class ActueleVertrekTijdenRequest extends AbstractApiRequest<ActueleVertrekTijden> {

    private final String station;

    public ActueleVertrekTijdenRequest(String station) {
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
