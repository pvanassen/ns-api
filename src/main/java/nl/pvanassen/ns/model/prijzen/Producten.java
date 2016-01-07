package nl.pvanassen.ns.model.prijzen;

import nl.pvanassen.ns.model.NsResult;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Producten element
 * 
 * @author Paul van Assen
 * 
 */
public class Producten implements NsResult {
    private final int tariefEenheden;
    private final List<Product> producten;

    Producten(int tariefEenheden, List<Product> producten) {
        super();
        this.tariefEenheden = tariefEenheden;
        this.producten = Collections.unmodifiableList(producten);
    }

    /**
     * @return 'Tarief eenheden', the amount of zones the trip costs
     */
    public int getTariefEenheden() {
        return tariefEenheden;
    }

    /**
     * @return List of products. As by the NS api, currently always two products: 'Enkele reis' and 'Dagretour'
     */
    public List<Product> getProducten() {
        return producten;
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
