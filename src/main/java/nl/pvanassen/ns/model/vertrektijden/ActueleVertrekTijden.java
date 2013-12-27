package nl.pvanassen.ns.model.vertrektijden;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden
 * 
 * @author Paul van Assen
 * 
 */
public class ActueleVertrekTijden {

    private final List<VertrekkendeTrein> vertrekkendeTreinen;

    ActueleVertrekTijden(List<VertrekkendeTrein> vertrekkendeTreinen) {
        this.vertrekkendeTreinen = Collections.unmodifiableList(vertrekkendeTreinen);
    }

    public List<VertrekkendeTrein> getVertrekkendeTreinen() {
        return vertrekkendeTreinen;
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
