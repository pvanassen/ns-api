package nl.pvanassen.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisStop implements Serializable {
    private final String naam;
    private final Date tijd;
    private final String spoor;
    private final boolean gewijzigdVertrekspoor;

    ReisStop(String naam, Date tijd, String spoor, boolean gewijzigdVertrekspoor) {
        super();
        this.naam = naam;
        this.tijd = tijd;
        this.spoor = spoor;
        this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
    }

    /**
     * @return Station name
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @return Date and time of arrival
     */
    public Date getTijd() {
        return tijd;
    }

    /**
     * @return Track if the train stops at the station
     */
    public String getSpoor() {
        return spoor;
    }

    /**
     * @return True if the departure track has changed for this ride, compared to a normal ride
     */
    public boolean isGewijzigdVertrekspoor() {
        return gewijzigdVertrekspoor;
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
