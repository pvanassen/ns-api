package nl.pvanassen.ns;

import java.util.Date;

import nl.pvanassen.ns.model.prijzen.Producten;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

public class RequestBuilder {

    // Only one instance possible. Pre-instantiating this
    private static final StationsRequest INSTANCE = new StationsRequest();

    /**
     * Hiding utility class constructor
     */
    private RequestBuilder() {
        super();
    }

    public static ApiRequest<ActueleVertrekTijden> getActueleVertrektijden(String station) {
        return new ActueleVertrekTijdenRequest(station);
    }

    public static ApiRequest<Stations> getStations() {
        return INSTANCE;
    }

    /**
     * Builds a request to return all 'Actuele storingen'
     * 
     * @return All 'Actuele storingen' request
     */
    public static ApiRequest<Storingen> getActueleStoringen() {
        return new StoringenEnWerkzaamhedenRequest(null, Boolean.TRUE, null);
    }

    /**
     * Builds a request that will return all 'Geplande werkzaamheden'
     * 
     * @return All 'Geplanden werkzaamheden' request
     */
    public static ApiRequest<Storingen> getGeplandeWerkzaamheden() {
        return new StoringenEnWerkzaamhedenRequest(null, null, Boolean.TRUE);
    }

    /**
     * Builds a request that will return all 'Geplande werkzaamheden'
     * 
     * @return All 'Geplanden werkzaamheden' request
     */
    public static ApiRequest<Storingen> getActueleStoringen(String station) {
        return new StoringenEnWerkzaamhedenRequest(station, null, null);
    }

    public static ReisadviesRequestBuilder getReisadviesRequestBuilder(String fromStation, String toStation) {
        return new ReisadviesRequestBuilder(fromStation, toStation);
    }

    public static ApiRequest<Producten> getPrijzen(String from, String to) {
        return getPrijzen(from, to, null, null);
    }

    public static ApiRequest<Producten> getPrijzen(String from, String to, String via) {
        return getPrijzen(from, to, via, null);
    }

    public static ApiRequest<Producten> getPrijzen(String from, String to, Date dateTime) {
        return getPrijzen(from, to, null, dateTime);
    }

    public static ApiRequest<Producten> getPrijzen(String from, String to, String via, Date dateTime) {
        return new PrijzenRequest(from, to, via, dateTime);
    }

    public static class ReisadviesRequestBuilder {

        private final String fromStation;

        private final String toStation;

        private String viaStation;

        private Integer previousAdvices;

        private Integer nextAdvices;

        private Date dateTime;

        private Boolean departure;

        private Boolean hslAllowed;

        private Boolean yearCard;

        ReisadviesRequestBuilder(String fromStation, String toStation) {
            this.fromStation = fromStation;
            this.toStation = toStation;
        }

        public ReisadviesRequestBuilder viaStation(String station) {
            this.viaStation = station;
            return this;
        }

        public ReisadviesRequestBuilder forDepartureTime(Date dateTime) {
            if (this.dateTime != null) {
                throw new IllegalArgumentException("Cannot set departure time, arival time already set");
            }
            this.dateTime = dateTime;
            this.departure = true;
            return this;
        }

        public ReisadviesRequestBuilder forArivalTime(Date dateTime) {
            if (this.dateTime != null) {
                throw new IllegalArgumentException("Cannot set arival time, departure time already set");
            }
            this.dateTime = dateTime;
            this.departure = false;
            return this;
        }

        public ReisadviesRequestBuilder includePastAdvices(int previousAdvices) {
            this.previousAdvices = previousAdvices;
            return this;
        }

        public ReisadviesRequestBuilder includeFutureAdvices(int nextAdvices) {
            this.nextAdvices = nextAdvices;
            return this;
        }

        public ReisadviesRequestBuilder withHsl() {
            this.hslAllowed = Boolean.TRUE;
            return this;
        }

        public ReisadviesRequestBuilder withoutHsl() {
            this.hslAllowed = Boolean.FALSE;
            return this;
        }

        public ReisadviesRequestBuilder userHasYearCard() {
            this.yearCard = Boolean.TRUE;
            return this;
        }

        public ReisadviesRequestBuilder userHasNoYearCard() {
            this.yearCard = Boolean.FALSE;
            return this;
        }

        public ReisadviesRequest build() {
            return new ReisadviesRequest(fromStation, toStation, viaStation, previousAdvices, nextAdvices, dateTime,
                    departure, hslAllowed, yearCard);
        }
    }

}
