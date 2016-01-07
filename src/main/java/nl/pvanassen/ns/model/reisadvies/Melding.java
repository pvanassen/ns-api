package nl.pvanassen.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class Melding implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final boolean ernstig;
    private final String text;

    Melding(String id, boolean ernstig, String text) {
        super();
        this.id = id;
        this.ernstig = ernstig;
        this.text = text;
    }

    /**
     * @return The melding ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return Indication of the severity, true means severe, false less severe
     */
    public boolean isErnstig() {
        return ernstig;
    }

    /**
     * @return The text of the 'melding'. The actual message
     */
    public String getText() {
        return text;
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
