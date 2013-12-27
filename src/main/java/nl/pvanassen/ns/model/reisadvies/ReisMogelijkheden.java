package nl.pvanassen.ns.model.reisadvies;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisMogelijkheden {

    private Melding melding;

    private final List<ReisMogelijkheid> reisMogelijkheid = new LinkedList<ReisMogelijkheid>();

    public List<ReisMogelijkheid> getReisMogelijkheid() {
        return reisMogelijkheid;
    }

    public Melding getMelding() {
        return melding;
    }

    public void setMelding(Melding melding) {
        this.melding = melding;
    }
}
