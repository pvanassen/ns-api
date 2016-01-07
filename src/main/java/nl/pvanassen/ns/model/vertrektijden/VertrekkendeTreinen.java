package nl.pvanassen.ns.model.vertrektijden;

import nl.pvanassen.ns.model.NsResultListWrapper;

import java.util.List;

/**
 * @author Paul van Assen
 */
public class VertrekkendeTreinen extends NsResultListWrapper<VertrekkendeTrein> {
    VertrekkendeTreinen(List<VertrekkendeTrein> vertrekkendeTreinen) {
        super(vertrekkendeTreinen);
    }
}
