package nl.pvanassen.ns.model.stations;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * For the exact meaning of all the fields, see <a
 * href="http://www.ns.nl/api/api#api-documentatie-stationslijst">documentatie stationslijst</a>
 * 
 * @author Paul van Assen
 * 
 */
public class Station implements Serializable {
    private final String code;
    private final String type;
    private final Namen namen;
    private final String land;
    private final int uicCode;
    private final double lat;
    private final double lon;
    private final List<String> synoniemen;

    Station(String code, String type, Namen namen, String land, int uicCode, double lat, double lon,
            List<String> synoniemen) {
        super();
        this.code = code;
        this.type = type;
        this.namen = namen;
        this.land = land;
        this.uicCode = uicCode;
        this.lat = lat;
        this.lon = lon;
        this.synoniemen = Collections.unmodifiableList(synoniemen);
    }

    /**
     * @return Stations code, two or three letters
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Type of station. For example 'knooppuntIntercitystation'
     */
    public String getType() {
        return type;
    }

    /**
     * @return Naming object containing short, middle and long names
     */
    public Namen getNamen() {
        return namen;
    }

    /**
     * @return Country, usually NL
     */
    public String getLand() {
        return land;
    }

    /**
     * @return Union Internationale des Chemins de fer code of a station
     */
    public int getUicCode() {
        return uicCode;
    }

    /**
     * @return Latitude of the station
     */
    public double getLat() {
        return lat;
    }

    /**
     * @return Longitude of the staion
     */
    public double getLon() {
        return lon;
    }

    /**
     * @return Alternative names for a station
     */
    public List<String> getSynoniemen() {
        return synoniemen;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}