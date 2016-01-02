package nl.pvanassen.ns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheid;

class ReisadviesRequest extends ApiRequest<List<ReisMogelijkheid>> {

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
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.viaStation = viaStation;
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
        requestString.append("fromStation=").append(urlEncode(fromStation)).append('&');
        requestString.append("toStation=").append(urlEncode(toStation)).append('&');
        if (viaStation != null && viaStation.trim().length() != 0) {
            requestString.append("viaStation=").append(urlEncode(viaStation)).append('&');
        }
        if (previousAdvices != null) {
            requestString.append("previousAdvices=").append(previousAdvices).append('&');
        }
        if (nextAdvices != null) {
            requestString.append("nextAdvices=").append(nextAdvices).append('&');
        }
        if (dateTime != null) {
            String dateAsString = new SimpleDateFormat(NsApi.DATETIME_FORMAT).format(dateTime);
            requestString.append("dateTime=").append(urlEncode(dateAsString))
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
