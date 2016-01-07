package nl.pvanassen.ns.model.reisadvies;

import nl.pvanassen.ns.model.NsResultListWrapper;

import java.util.List;

/**
 * @author Paul van Assen
 */
public class ReisMogelijkheden extends NsResultListWrapper<ReisMogelijkheid> {
    ReisMogelijkheden(List<ReisMogelijkheid> reisMogelijkheden) {
        super(reisMogelijkheden);
    }
}
