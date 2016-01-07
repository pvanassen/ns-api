package nl.pvanassen.ns.model.prijzen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Product element
 * 
 * @author Paul van Assen
 * 
 */
public class Product implements Serializable {
    private final String naam;
    private final List<Prijs> prijzen;

    Product(String naam, List<Prijs> prijzen) {
        super();
        this.naam = naam;
        this.prijzen = Collections.unmodifiableList(prijzen);
    }

    /**
     * @return Product name, currently 'Enkele reis' or 'Dag retour'
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @return List of actual prices
     */
    public List<Prijs> getPrijzen() {
        return prijzen;
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
