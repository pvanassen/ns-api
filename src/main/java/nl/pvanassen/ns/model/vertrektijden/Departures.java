package nl.pvanassen.ns.model.vertrektijden;

import lombok.Builder;
import lombok.Getter;
import nl.pvanassen.ns.model.NsResultListWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class Departures extends NsResultListWrapper<Departure> {

    @Builder
    private Departures(@NotNull final List<Departure> inner, final String source) {
        super(inner);
        this.source = source;
    }

    @NotNull
    private final String source;

}
