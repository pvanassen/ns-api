package nl.pvanassen.ns.model.vertrektijden;

import static java.lang.Boolean.parseBoolean;
import static java.util.Collections.unmodifiableList;
import static nl.pvanassen.ns.NsApi.DATETIME_FORMATTER;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle to de-serialize the actuele vertrektijden response. For more information see <a
 * href="http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden">documentatie actuele vertrektijden</a>
 * 
 * @author Paul van Assen
 * 
 */
public class ActueleVertrekTijdenHandle implements Handle<VertrekkendeTreinen> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.handle.Handle#getModel(java.io.InputStream)
     */
    @NotNull
    @Override
    public VertrekkendeTreinen getModel(@NotNull final InputStream stream) {
        try {
            final Xml xml = Xml.getXml(stream, "ActueleVertrekTijden");
            final List<VertrekkendeTrein> vertrekkendeTreinen = xml.children("VertrekkendeTrein").stream()
                    .map(this::getVertrekkendeTrein)
                    .collect(Collectors.toList());
            return VertrekkendeTreinen.builder()
                    .vertrekkendeTreinen(vertrekkendeTreinen)
                    .build();
        }
        catch (DateTimeParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

    private VertrekkendeTrein getVertrekkendeTrein(final Xml vertrekkendeTreinXml) {
        final int ritNummer = Integer.parseInt(vertrekkendeTreinXml.child("RitNummer").content());
        final LocalDateTime vertrekTijd = LocalDateTime.parse(vertrekkendeTreinXml.child("VertrekTijd").content(), DATETIME_FORMATTER);
        final String vertrekVertraging = vertrekkendeTreinXml.child("VertrekVertraging").content();

        final int vertrekVertragingMinuten = vertrekkendeTreinXml.childIfPresent("VertrekVertraging")
                .map(Xml::content)
                .map(this::getVertrekVertraging)
                .orElse(0);

        final String vertrekVertragingTekst = vertrekkendeTreinXml.child("VertrekVertragingTekst").content();
        final String eindBestemming = vertrekkendeTreinXml.child("EindBestemming").content();
        final String treinSoort = vertrekkendeTreinXml.child("TreinSoort").content();
        final String routeTekst = vertrekkendeTreinXml.child("RouteTekst").content();
        final String vervoerder = vertrekkendeTreinXml.child("Vervoerder").content();
        final String vertrekSpoor = vertrekkendeTreinXml.child("VertrekSpoor").content();
        final boolean gewijzigdVertrekspoor = parseBoolean(vertrekkendeTreinXml.child("VertrekSpoor").attr(
                "wijziging"));

        final List<String> opmerkingen = vertrekkendeTreinXml.children("Opmerkingen")
                .stream()
                .map(opm -> opm.child("Opmerking"))
                .map(Xml::content)
                .collect(Collectors.toList());

        final String reisTip = vertrekkendeTreinXml.child("ReisTip").content();

        return VertrekkendeTrein.builder()
                        .eindBestemming(eindBestemming)
                        .gewijzigdVertrekspoor(gewijzigdVertrekspoor)
                        .opmerkingen(unmodifiableList(opmerkingen))
                        .reisTip(reisTip)
                        .ritNummer(ritNummer)
                        .routeTekst(routeTekst)
                        .treinSoort(treinSoort)
                        .vertrekSpoor(vertrekSpoor)
                        .vertrekTijd(vertrekTijd)
                        .vertrekVertraging(vertrekVertraging)
                        .vertrekVertragingMinuten(vertrekVertragingMinuten)
                        .vertrekVertragingTekst(vertrekVertragingTekst)
                        .vervoerder(vervoerder)
                        .build();
    }

    private Integer getVertrekVertraging(final String vertrekVertraging) {
        try {
            return Integer.parseInt(vertrekVertraging.replace("PT", "")
                    .replace("M", ""));
        }
        catch (NumberFormatException e) {
            logger.warn("Error parsing vertrek vertraging minuten into minutes", e);
        }
        return 0;
    }
}
