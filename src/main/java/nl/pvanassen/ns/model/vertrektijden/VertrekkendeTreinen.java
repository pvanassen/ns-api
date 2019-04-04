package nl.pvanassen.ns.model.vertrektijden;

import static java.util.Collections.unmodifiableList;

import java.util.List;

import nl.pvanassen.ns.model.NsResultListWrapper;

import lombok.Builder;

/**
 * @author Paul van Assen
 */
public class VertrekkendeTreinen extends NsResultListWrapper<VertrekkendeTrein> {
    @Builder
    private VertrekkendeTreinen(List<VertrekkendeTrein> vertrekkendeTreinen) {
        super(unmodifiableList(vertrekkendeTreinen));
    }
}
