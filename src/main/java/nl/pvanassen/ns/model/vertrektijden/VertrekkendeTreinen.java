package nl.pvanassen.ns.model.vertrektijden;

import java.util.List;

import nl.pvanassen.ns.model.NsResultListWrapper;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;

/**
 * @author Paul van Assen
 */
public class VertrekkendeTreinen extends NsResultListWrapper<VertrekkendeTrein> {
    @Builder
    private VertrekkendeTreinen(@NotNull final List<VertrekkendeTrein> vertrekkendeTreinen) {
        super(vertrekkendeTreinen);
    }
}
