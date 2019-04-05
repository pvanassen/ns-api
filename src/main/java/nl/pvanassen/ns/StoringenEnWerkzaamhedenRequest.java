package nl.pvanassen.ns;

import nl.pvanassen.ns.model.storingen.Storingen;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Request object for 'Storingen en werkzaamheden'. For an explanation of all parameters, please see:
 * <a href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">API Documentatie storingen en werkzaamheden</a>
 * 
 * Overzicht
 * 
 * De webservice voor de storingen en werkzaamheden maakt het mogelijk informatie op te vragen over storingen en/of
 * werkzaamheden.
 * 
 * De volgende informatie kan worden opgevraagd:
 * <ul>
 * <li>actuele storingen (=ongeplande storingen + actuele werkzaamheden)</li>
 * <li>geplande werkzaamheden (=geplande werkzaamheden)</li>
 * <li>actuele storingen voor een gespecificeerd station (=ongeplande storingen + actuele werkzaamheden)</li>
 * </ul>
 * 
 * @author Paul van Assen
 * 
 */
class StoringenEnWerkzaamhedenRequest extends ApiRequest<Storingen> {

    private final String station;

    private final Boolean actual;

    private final Boolean unplanned;

    StoringenEnWerkzaamhedenRequest(@Nullable final String station, @Nullable final Boolean actual, @Nullable final Boolean unplanned) {
        this.station = UrlParamHelper.encode(station);
        this.actual = actual;
        this.unplanned = unplanned;
    }

    @NotNull
    @Override
    String getPath() {
        return "ns-api-storingen";
    }

    @NotNull
    @Override
    String getRequestString() {
        StringBuilder requestString = new StringBuilder();
        if (station != null && station.trim().length() != 0) {
            requestString.append("station=").append(station).append('&');
        }
        if (actual != null) {
            requestString.append("actual=").append(actual).append('&');
        }
        if (unplanned != null) {
            requestString.append("unplanned=").append(unplanned).append('&');
        }
        return requestString.toString();
    }
}
