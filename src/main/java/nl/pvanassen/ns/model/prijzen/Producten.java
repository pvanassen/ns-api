package nl.pvanassen.ns.model.prijzen;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Producten element
 * 
 * @author Paul van Assen
 * 
 */
public class Producten {

    private int tariefEenheden;

    private final List<Product> producten = new LinkedList<Product>();

    public int getTariefEenheden() {
        return tariefEenheden;
    }

    public void setTariefEenheden(int tariefEenheden) {
        this.tariefEenheden = tariefEenheden;
    }

    public List<Product> getProducten() {
        return producten;
    }

}
