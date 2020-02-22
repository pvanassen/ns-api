package nl.pvanassen.ns.model.vertrektijden;

import lombok.extern.slf4j.Slf4j;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ActueleVertrekTijdenHandleV2 implements Handle<Departures> {

    private final DepartureMapping departureMapping = new DepartureMapping();

    @NotNull
    @Override
    public Departures getModel(@NotNull final InputStream stream) {
        final JsonResponse response = Response.getJson(stream).child("payload");
        final List<Departure> result = response.children("departures")
                .stream()
                .map(JsonResponse::asPresent)
                .map(departureMapping::map)
                .collect(Collectors.toList());
        return Departures.builder().source(response.child("source").content()).inner(result).build();
    }

}
