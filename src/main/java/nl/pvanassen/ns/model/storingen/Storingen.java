package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.NsResult;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden
 * 
 * @author Paul van Assen
 * 
 */
public class Storingen implements NsResult {
    private final List<Storing> ongeplandeStoringen;
    private final List<Storing> geplandeStoringen;

    Storingen(List<Storing> ongeplandeStoringen, List<Storing> geplandeStoringen) {
        this.ongeplandeStoringen = Collections.unmodifiableList(ongeplandeStoringen);
        this.geplandeStoringen = Collections.unmodifiableList(geplandeStoringen);
    }

    /**
     * @return List of unplanned disruptions
     */
    public List<Storing> getOngeplandeStoringen() {
        return ongeplandeStoringen;
    }

    /**
     * @return List of planned disruptions
     */
    public List<Storing> getGeplandeStoringen() {
        return geplandeStoringen;
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
