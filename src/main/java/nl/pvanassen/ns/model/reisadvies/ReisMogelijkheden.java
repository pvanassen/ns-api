package nl.pvanassen.ns.model.reisadvies;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisMogelijkheden {

    private final List<ReisMogelijkheid> reisMogelijkheid;

    ReisMogelijkheden(List<ReisMogelijkheid> reisMogelijkheid) {
        super();
        this.reisMogelijkheid = Collections.unmodifiableList(reisMogelijkheid);
    }

    public List<ReisMogelijkheid> getReisMogelijkheid() {
        return reisMogelijkheid;
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
