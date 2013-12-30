package nl.pvanassen.ns;

import java.util.Date;
import java.util.List;

import nl.pvanassen.ns.model.prijzen.Producten;
import nl.pvanassen.ns.model.stations.Station;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

/**
 * Request builder helper class. This class builds concrete implementations of the {@link ApiRequest} abstract class.
 * Each of the calls corresponds with calls defined on <a href="http://www.ns.nl/api/api">NS API</a>.
 * 
 * @author Paul van Assen
 * 
 */
public class RequestBuilder {

    // Only one instance possible. Pre-instantiating this
    private static final StationsRequest INSTANCE = new StationsRequest();

    /**
     * Hiding utility class constructor
     */
    private RequestBuilder() {
        super();
    }

    /**
     * This method builds a request to get the <a
     * href="http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden">actuele vertrektijden</a> by station.
     * 
     * @param station A train station to get the vertrektijden from. See the documentation for which notations are
     *            allowed
     * @return The vertrektijden for this station
     */
    public static ApiRequest<List<VertrekkendeTrein>> getActueleVertrektijden(String station) {
        return new ActueleVertrekTijdenRequest(station);
    }

    /**
     * This method builds a request to get all available <a
     * href="http://www.ns.nl/api/api#api-documentatie-stationslijst">stations</a>.
     * 
     * @return An object containing all stations
     */
    public static ApiRequest<List<Station>> getStations() {
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
