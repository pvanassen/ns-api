package nl.pvanassen.ns.model.prijzen;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Product element
 * 
 * @author Paul van Assen
 * 
 */
public class Product {

    private String naam;

    private final List<Prijs> prijzen = new LinkedList<Prijs>();

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Prijs> getPrijzen() {
        return prijzen;
    }

}
