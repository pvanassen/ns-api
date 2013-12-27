package nl.pvanassen.ns.model.reisadvies;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisStop {

    private final String naam;

    private final Date tijd;

    private final int spoor;

    private final boolean gewijzigdVertrekspoor;

    ReisStop(String naam, Date tijd, int spoor, boolean gewijzigdVertrekspoor) {
        super();
        this.naam = naam;
        this.tijd = tijd;
        this.spoor = spoor;
        this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
    }

    public String getNaam() {
        return naam;
    }

    public Date getTijd() {
        return tijd;
    }

    public int getSpoor() {
        return spoor;
    }

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
