package nl.pvanassen.ns.model.prijzen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Prijs element
 * 
 * @author Paul van Assen
 * 
 */
public class Prijs {

    private final String korting;

    private final int klasse;

    private final int prijs;

    Prijs(String korting, int klasse, int prijs) {
        super();
        this.korting = korting;
        this.klasse = klasse;
        this.prijs = prijs;
    }

    public String getKorting() {
        return korting;
    }

    public int getKlasse() {
        return klasse;
    }

    public int getPrijs() {
        return prijs;
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
