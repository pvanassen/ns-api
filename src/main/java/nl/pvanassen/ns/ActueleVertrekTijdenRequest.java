package nl.pvanassen.ns;

import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTreinen;

import org.jetbrains.annotations.NotNull;

class ActueleVertrekTijdenRequest extends ApiRequest<VertrekkendeTreinen> {

    private final String station;

    ActueleVertrekTijdenRequest(@NotNull final String station) {
        this.station = UrlParamHelper.encode(station);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getPath()
     */
    @NotNull
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
    @NotNull
    @Override
    String getRequestString() {
        return "station=" + station;
    }
}
