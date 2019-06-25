package nl.pvanassen.ns.model.storingen;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.parser.JsonResponse;
import nl.pvanassen.ns.parser.Response;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class StoringenHandleV2 implements Handle<Verstoringen> {

    private final VerstoringMapping verstoringMapping = new VerstoringMapping();

    @NotNull
    @Override
    public Verstoringen getModel(@NotNull final InputStream stream) {
        final JsonResponse response = Response.getJson(stream);
        final List<Verstoring> result = response.children("payload")
                .stream()
                .map(jsonResponse -> jsonResponse.child("verstoring").asPresent())
                .map(verstoringMapping::map)
                .collect(Collectors.toList());
        return new Verstoringen(result);
    }

}
