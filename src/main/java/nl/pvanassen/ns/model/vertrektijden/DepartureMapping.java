package nl.pvanassen.ns.model.vertrektijden;

import nl.pvanassen.ns.model.Mapping;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.ResponsePresent;
import org.jetbrains.annotations.NotNull;

public class DepartureMapping implements Mapping<Departure> {

    @NotNull
    @Override
    public Departure map(@NotNull ResponsePresent<JsonResponse> jsonResponse) {
        return null;
    }
}
