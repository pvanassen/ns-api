package nl.pvanassen.ns;

import nl.pvanassen.ns.model.prijzen.Prijzen;
import nl.pvanassen.ns.model.stations.Station;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

import java.util.Date;
import java.util.List;

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
        return RequestBuilder.INSTANCE;
    }

    /**
     * Builds a request to return all 'Actuele storingen' See <a
     * href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">actuele storingen</a>, bullet 1
     * 
     * @return All 'Actuele storingen' request
     */
    public static ApiRequest<Storingen> getActueleStoringen() {
        return new StoringenEnWerkzaamhedenRequest(null, Boolean.TRUE, null);
    }

    /**
     * Builds a request that will return all 'Geplande werkzaamheden'
     * 
     * See <a href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">actuele storingen</a>, bullet
     * 2
     * 
     * @return All 'Geplanden werkzaamheden' request
     */
    public static ApiRequest<Storingen> getGeplandeWerkzaamheden() {
        return new StoringenEnWerkzaamhedenRequest(null, null, Boolean.TRUE);
    }

    /**
     * Builds a request that will return all 'Actuele storingen voor een gespecificeerd station'
     * 
     * See <a href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">actuele storingen</a>, bullet
     * 3
     * 
     * @param station A train station to get the vertrektijden from. See the documentation for which notations are
     *            allowed
     * @return All 'Actuele storingen' request for a station
     */
    public static ApiRequest<Storingen> getActueleStoringen(String station) {
        return new StoringenEnWerkzaamhedenRequest(station, null, null);
    }

    /**
     * Builds a request to get all fares for a ride between station from, to station to. See <a
     * href="http://www.ns.nl/api/api#api-documentatie-prijzen">prijzen</a>
     * 
     * @param fromStation Starting point of the trip
     * @param toStation End point of the trip
     * @return Request for getting the fares
     */
    public static ApiRequest<Prijzen> getPrijzen(String fromStation, String toStation) {
        return RequestBuilder.getPrijzen(fromStation, toStation, null, null);
    }

    /**
     * Builds a request to get all fares for a ride between station from, to station to. See <a
     * href="http://www.ns.nl/api/api#api-documentatie-prijzen">prijzen</a>
     * 
     * @param fromStation Starting point of the trip
     * @param toStation End point of the trip
     * @param viaStation Also go to this station
     * @return Request for getting the fares
     */
    public static ApiRequest<Prijzen> getPrijzen(String fromStation, String toStation, String viaStation) {
        return RequestBuilder.getPrijzen(fromStation, toStation, viaStation, null);
    }

    /**
     * Builds a request to get all fares for a ride between station from, to station to. See <a
     * href="http://www.ns.nl/api/api#api-documentatie-prijzen">prijzen</a>
     * 
     * @param fromStation Starting point of the trip
     * @param toStation End point of the trip
     * @param dateTime Date and time to use for getting the fares.
     * @return Request for getting the fares
     */
    public static ApiRequest<Prijzen> getPrijzen(String fromStation, String toStation, Date dateTime) {
        return RequestBuilder.getPrijzen(fromStation, toStation, null, dateTime);
    }

    /**
     * Builds a request to get all fares for a ride between station from, to station to. See <a
     * href="http://www.ns.nl/api/api#api-documentatie-prijzen">prijzen</a>
     * 
     * @param fromStation Starting point of the trip
     * @param toStation End point of the trip
     * @param viaStation Also go to this station
     * @param dateTime Date and time to use for getting the fares.
     * @return Request for getting the fares
     */
    public static ApiRequest<Prijzen> getPrijzen(String fromStation, String toStation, String viaStation,
            Date dateTime) {
        return new PrijzenRequest(fromStation, toStation, viaStation, dateTime);
    }

    /**
     * Because the reis advies call is quite complex, this call will return a request builder. Through the fluent API
     * interface you can set all parameters. Call build() at the end of your call to get the actual request. See <a
     * href="http://www.ns.nl/api/api#api-documentatie-reisadviezen">reis adviezen</a> for more information
     * 
     * @param fromStation Starting point of your trip
     * @param toStation End point of your trip
     * @return A request builder which can be used to build the request through a fluent API.
     */
    public static ReisadviesRequestBuilder getReisadviesRequestBuilder(String fromStation, String toStation) {
        return new ReisadviesRequestBuilder(fromStation, toStation);
    }

    /**
     * Reis advies request builder used to build the request in a fluent way.
     * 
     * @author Paul van Assen
     * 
     */
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

        /**
         * Adds a route-through station (via station)
         * 
         * @param station Code, short name or full name of a station
         * @return The builder
         */
        public ReisadviesRequestBuilder viaStation(String station) {
            viaStation = station;
            return this;
        }

        /**
         * Give the reis advies from a departure time. After this call, you cannot call forArrivalTime, this conflict
         * each other
         * 
         * @param dateTime The departure time to use
         * @return The builder
         */
        public ReisadviesRequestBuilder forDepartureTime(Date dateTime) {
            if (this.dateTime != null) {
                throw new IllegalArgumentException("Cannot set departure time, arival time already set");
            }
            this.dateTime = dateTime;
            departure = true;
            return this;
        }

        /**
         * Give the reis advies from an arrival time. After this call, you cannot call forDepartureTime, this conflict
         * each other
         * 
         * @param dateTime The arrival time to use
         * @return The builder
         */
        public ReisadviesRequestBuilder forArrivalTime(Date dateTime) {
            if (this.dateTime != null) {
                throw new IllegalArgumentException("Cannot set arival time, departure time already set");
            }
            this.dateTime = dateTime;
            departure = false;
            return this;
        }

        /**
         * Specify how many advices before the given time (or now if no time is given) should be returned. If left empty
         * 5 are returned per default of the NS.
         * 
         * @param previousAdvices Amount of advices to return before the given time (or now if no time is given)
         * @return The builder
         */
        public ReisadviesRequestBuilder includePastAdvices(int previousAdvices) {
            this.previousAdvices = previousAdvices;
            return this;
        }

        /**
         * Specify how many advices after the given time (or now if no time is given) should be returned. If left empty
         * 5 are returned per default of the NS.
         * 
         * @param nextAdvices Amount of advices to return after the given time (or now if no time is given)
         * @return The builder
         */
        public ReisadviesRequestBuilder includeFutureAdvices(int nextAdvices) {
            this.nextAdvices = nextAdvices;
            return this;
        }

        /**
         * The advices returned take into account the user is willing to travel with the HSL (High speed lines)
         * 
         * @return The builder
         */
        public ReisadviesRequestBuilder withHsl() {
            hslAllowed = Boolean.TRUE;
            return this;
        }

        /**
         * The advices returned take into account the user does not want to travel with the HSL (High speed lines)
         * 
         * @return The builder
         */
        public ReisadviesRequestBuilder withoutHsl() {
            hslAllowed = Boolean.FALSE;
            return this;
        }

        /**
         * The advices returned take into account the user has a 'ns jaar kaart', which allows the user to travel
         * everywhere. In some cases traveling through a different route is more expensive but faster. If a user has a
         * 'jaarkaart' this expense does not count for the user.
         * 
         * @return The builder
         */
        public ReisadviesRequestBuilder userHasYearCard() {
            yearCard = Boolean.TRUE;
            return this;
        }

        /**
         * The advices returned take into account the user does not have a 'ns jaar kaart'. When calculating advices,
         * only direct routes are taken into account and faster but more expensive routes are left out.
         * 
         * @return The builder
         */
        public ReisadviesRequestBuilder userHasNoYearCard() {
            yearCard = Boolean.FALSE;
            return this;
        }

        /**
         * Builds the request
         * 
         * @return The request for getting 'reis adviezen'
         */
        public ReisadviesRequest build() {
            return new ReisadviesRequest(fromStation, toStation, viaStation, previousAdvices, nextAdvices, dateTime,
                    departure, hslAllowed, yearCard);
        }
    }

}
