package nl.pvanassen.ns;

import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;

import java.text.SimpleDateFormat;
import java.util.Date;

class ReisadviesRequest extends ApiRequest<ReisMogelijkheden> {

    private final String fromStation;

    private final String toStation;

    private final String viaStation;

    private final Integer previousAdvices;

    private final Integer nextAdvices;

    private final Date dateTime;

    private final Boolean departure;

    private final Boolean hslAllowed;

    private final Boolean yearCard;

    ReisadviesRequest(String fromStation, String toStation, String viaStation, Integer previousAdvices,
            Integer nextAdvices, Date dateTime, Boolean departure, Boolean hslAllowed, Boolean yearCard) {
        super();
        this.fromStation = UrlParamHelper.encode(fromStation);
        this.toStation = UrlParamHelper.encode(toStation);
        this.viaStation = UrlParamHelper.encode(viaStation);
        this.previousAdvices = previousAdvices;
        this.nextAdvices = nextAdvices;
        this.dateTime = dateTime;
        this.departure = departure;
        this.hslAllowed = hslAllowed;
        this.yearCard = yearCard;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getPath()
     */
    @Override
    String getPath() {
        return "ns-api-treinplanner";
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getRequestString()
     */
    @Override
    String getRequestString() {
        StringBuilder requestString = new StringBuilder();
        requestString.append("fromStation=").append(fromStation).append('&');
        requestString.append("toStation=").append(toStation).append('&');
        if (viaStation != null && viaStation.trim().length() != 0) {
            requestString.append("viaStation=").append(viaStation).append('&');
        }
        if (previousAdvices != null) {
            requestString.append("previousAdvices=").append(previousAdvices).append('&');
        }
        if (nextAdvices != null) {
            requestString.append("nextAdvices=").append(nextAdvices).append('&');
        }
        if (dateTime != null) {
            requestString.append("dateTime=").append(new SimpleDateFormat(NsApi.DATETIME_FORMAT).format(dateTime))
                    .append('&');
        }
        if (departure != null) {
            requestString.append("departure=").append(departure).append('&');
        }
        if (hslAllowed != null) {
            requestString.append("hslAllowed=").append(hslAllowed).append('&');
        }
        if (yearCard != null) {
            requestString.append("yearCard=").append(yearCard).append('&');
        }
        return requestString.toString();
    }

}
