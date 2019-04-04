package nl.pvanassen.ns.model.reisadvies;

import java.util.List;

import nl.pvanassen.ns.model.NsResultListWrapper;

import org.jetbrains.annotations.NotNull;

/**
 * @author Paul van Assen
 */
public class ReisMogelijkheden extends NsResultListWrapper<ReisMogelijkheid> {
    ReisMogelijkheden(@NotNull final List<ReisMogelijkheid> reisMogelijkheden) {
        super(reisMogelijkheden);
    }
}
