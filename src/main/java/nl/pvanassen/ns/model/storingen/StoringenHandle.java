package nl.pvanassen.ns.model.storingen;

import static java.util.Collections.unmodifiableList;
import static nl.pvanassen.ns.NsApi.DATETIME_FORMATTER;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

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
            final Xml xml = Xml.getXml(stream, "Storingen");

            final List<Storing> ongeplandeStoringen = getStoringen(xml.childrenIfPresent("Ongepland"));
            final List<Storing> geplandeStoringen = getStoringen(xml.childrenIfPresent("Gepland"));

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

    private List<Storing> getStoringen(final Optional<List<Xml>> storingenList) {
        return storingenList
                .map(Collection::stream)
                .map(xmlStream -> xmlStream.flatMap(xml -> xml.children("Storing").stream()))
                .map(xmlStream -> xmlStream.map(this::getStoring))
                .map(storingStream -> storingStream.collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

    private Storing getStoring(final Xml storingXml) {
        final String id = storingXml.child("id").content();
        final String traject = storingXml.child("Traject").content();
        final String periode = storingXml.child("Periode").content();
        final String reden = storingXml.child("Reden").content();
        final String advies = storingXml.child("Advies").content();
        final String bericht = storingXml.child("Bericht").content();
        final LocalDateTime datum = storingXml.childIfPresent("Datum")
                .map(Xml::content)
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
