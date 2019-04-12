package nl.pvanassen.ns.model.storingen;

import static java.util.Collections.unmodifiableList;
import static nl.pvanassen.ns.NsApi.DATETIME_FORMATTER;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.parser.Response;
import nl.pvanassen.ns.parser.XmlResponse;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle for parsing disruption xml messages. For more information see <a
 * href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">documentatie storinge en
 * werkzaamheden</a>.
 * 
 * @author Paul van Assen
 * 
 */
public class StoringenHandle implements Handle<Storingen> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.handle.Handle#getModel(java.io.InputStream)
     */
    @NotNull
    @Override
    public Storingen getModel(@NotNull final InputStream stream) {
        try {
            final XmlResponse response = Response.getXml(stream, "Storingen");

            final List<Storing> ongeplandeStoringen = getStoringen(response.children("Ongepland"));
            final List<Storing> geplandeStoringen = getStoringen(response.children("Gepland"));

            return Storingen.builder()
                    .geplandeStoringen(unmodifiableList(geplandeStoringen))
                    .ongeplandeStoringen(unmodifiableList(ongeplandeStoringen))
                    .build();
        }
        catch (DateTimeParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

    private List<Storing> getStoringen(final List<XmlResponse> storingenList) {
        return storingenList
                .stream()
                .flatMap(xml -> xml.children("Storing").stream())
                .map(this::getStoring)
                .collect(Collectors.toList());
    }

    private Storing getStoring(final XmlResponse storingResponse) {
        final String id = storingResponse.requiredChild("id").content();
        final String traject = storingResponse.requiredChild("Traject").content();
        final String periode = storingResponse.child("Periode").content();
        final String reden = storingResponse.requiredChild("Reden").content();
        final String advies = storingResponse.child("Advies").content();
        final String bericht = storingResponse.requiredChild("Bericht").content();
        final LocalDateTime datum = storingResponse.childIfPresent("Datum")
                .map(Response::content)
                .map(date -> LocalDateTime.parse(date, DATETIME_FORMATTER))
                .orElse(null);
        return Storing.builder()
                .advies(advies)
                .bericht(bericht)
                .datum(datum)
                .id(id)
                .periode(periode)
                .reden(reden)
                .traject(traject)
                .build();
    }

}
