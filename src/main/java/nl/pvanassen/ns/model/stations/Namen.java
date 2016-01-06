package nl.pvanassen.ns.model.stations;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http://www.ns.nl/api/api#api-documentatie-stationslijst
 * 
 * @author Paul van Assen
 * 
 */
public class Namen {
    private final String kort;
    private final String middel;
    private final String lang;

    Namen(String kort, String middel, String lang) {
        super();
        this.kort = kort;
        this.middel = middel;
        this.lang = lang;
    }

    /**
     * @return Short name of a station, max 10 chars
     */
    public String getKort() {
        return kort;
    }

    /**
     * @return Middle-long name of a station, max 16 chars
     */
    public String getMiddel() {
        return middel;
    }

    /**
     * @return Long name of a station, max 25 chars
     */
    public String getLang() {
        return lang;
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
