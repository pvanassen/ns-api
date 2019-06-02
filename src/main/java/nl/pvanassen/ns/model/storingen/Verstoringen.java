package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.model.NsResultListWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Verstoringen extends NsResultListWrapper<Verstoring> {

    Verstoringen(@NotNull List<Verstoring> inner) {
        super(inner);
    }
}
