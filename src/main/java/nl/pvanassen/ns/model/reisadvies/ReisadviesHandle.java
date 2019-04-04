package nl.pvanassen.ns.model.reisadvies;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.parseBoolean;
import static java.time.LocalDateTime.parse;
import static java.util.Collections.unmodifiableList;
import static java.util.Optional.ofNullable;
import static nl.pvanassen.ns.NsApi.DATETIME_FORMATTER;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle to process the 'reisadvies' XML as defined in <a
 * href="http://www.ns.nl/api/api#api-documentatie-reisadviezen">documentatie reisadviezen</a>
 * 
 * @author Paul van Assen
 * 
 */
public class ReisadviesHandle implements Handle<ReisMogelijkheden> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.handle.Handle#getModel(java.io.InputStream)
     */
    @NotNull
    @Override
    public ReisMogelijkheden getModel(@NotNull final InputStream stream) {
        try {
            final Xml xml = Xml.getXml(stream, "ReisMogelijkheden");
            final List<ReisMogelijkheid> reisMogelijkheden = xml.children("ReisMogelijkheid")
                    .stream()
                    .map(this::getReisMogelijkheid)
                    .collect(Collectors.toList());
            return new ReisMogelijkheden(reisMogelijkheden);
        }
        catch (DateTimeParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

    private ReisMogelijkheid getReisMogelijkheid(final Xml reisMogelijkheidXml) {
        final List<Melding> meldingen = reisMogelijkheidXml.childrenIfPresent("Melding")
                .map(xml -> xml.stream().map(this::getMelding).collect(Collectors.toList()))
                .orElseGet(LinkedList::new);

        final int aantalOverstappen = Integer.parseInt(reisMogelijkheidXml.child("AantalOverstappen").content());
        final int geplandeReisTijdMinuten = getReistijdInMinuten(reisMogelijkheidXml.child("GeplandeReisTijd")
                .content());
        final int actueleReisTijdMinuten = getReistijdInMinuten(reisMogelijkheidXml.child("ActueleReisTijd")
                .content());

        final boolean optimaal = reisMogelijkheidXml.childIfPresent("Optimaal")
                .map(xml -> parseBoolean(xml.content()))
                .orElse(TRUE);

        final LocalDateTime geplandeVertrekTijd = parse(reisMogelijkheidXml.child("GeplandeVertrekTijd").content(), DATETIME_FORMATTER);
        final LocalDateTime actueleVertrekTijd = parse(reisMogelijkheidXml.child("ActueleVertrekTijd").content(), DATETIME_FORMATTER);
        final LocalDateTime geplandeAankomstTijd = parse(reisMogelijkheidXml.child("GeplandeAankomstTijd").content(), DATETIME_FORMATTER);
        final LocalDateTime actueleAankomstTijd = parse(reisMogelijkheidXml.child("ActueleAankomstTijd").content(), DATETIME_FORMATTER);

        final String aankomstVertraging = reisMogelijkheidXml.child("AankomstVertraging").content();
        final String status = reisMogelijkheidXml.child("Status").content();

        final List<ReisDeel> reisDelen = reisMogelijkheidXml.children("ReisDeel")
                .stream()
                .map(this::getReisdeel)
                .collect(Collectors.toList());

        return ReisMogelijkheid.builder()
                        .aankomstVertraging(aankomstVertraging)
                        .aantalOverstappen(aantalOverstappen)
                        .actueleAankomstTijd(actueleAankomstTijd)
                        .actueleReisTijdMinuten(actueleReisTijdMinuten)
                        .actueleVertrekTijd(actueleVertrekTijd)
                        .geplandeAankomstTijd(geplandeAankomstTijd)
                        .geplandeReisTijdMinuten(geplandeReisTijdMinuten)
                        .geplandeVertrekTijd(geplandeVertrekTijd)
                        .meldingen(unmodifiableList(meldingen))
                        .optimaal(optimaal)
                        .reisDelen(unmodifiableList(reisDelen))
                        .status(status)
                        .build();

    }

    private Melding getMelding(final Xml meldingXml) {
        final String id = meldingXml.child("Id").content();
        final boolean ernstig = parseBoolean(meldingXml.child("Ernstig").content());
        final String text = meldingXml.child("Text").content();
        return Melding.builder()
                .id(id)
                .ernstig(ernstig)
                .text(text)
                .build();
    }

    private ReisDeel getReisdeel(Xml reisDeelXml) {
        final String reisSoort = reisDeelXml.attr("reisSoort");
        final String vervoerder = reisDeelXml.child("Vervoerder").content();
        final String vervoerType = reisDeelXml.child("VervoerType").content();
        final int ritNummer = Integer.parseInt(reisDeelXml.child("RitNummer").content());
        final String statusReisdeel = reisDeelXml.child("Status").content();
        final String geplandeStoringId = reisDeelXml.child("GeplandeStoringId").content();
        final String ongeplandeStoringId = reisDeelXml.child("OngeplandeStoringId").content();
        final List<ReisStop> reisStops = reisDeelXml.children("ReisStop")
                .stream()
                .map(this::getReisStop)
                .collect(Collectors.toList());

        final List<String> reisDetails = reisDeelXml.child("Reisdetails").children("Reisdetail")
                .stream()
                .map(Xml::content)
                .collect(Collectors.toList());

        return ReisDeel.builder()
                .reisSoort(reisSoort)
                .vervoerder(vervoerder)
                .vervoerType(vervoerType)
                .ritNummer(ritNummer)
                .status(statusReisdeel)
                .reisStops(reisStops)
                .ongeplandeStoringId(ongeplandeStoringId)
                .geplandeStoringId(geplandeStoringId)
                .reisDetails(reisDetails)
                .build();
    }

    private ReisStop getReisStop(final Xml reisStopXml) {
        final String naam = reisStopXml.child("Naam").content();
        final LocalDateTime tijd = LocalDateTime.parse(reisStopXml.child("Tijd").content(), DATETIME_FORMATTER);
        final String spoor = reisStopXml.child("Spoor").content();
        final boolean gewijzigdVertrekspoor = ofNullable(spoor).map(ignored -> parseBoolean(reisStopXml.child("Spoor").attr("wijziging"))).orElse(false);

        return ReisStop.builder()
                .naam(naam)
                .tijd(tijd)
                .spoor(spoor)
                .gewijzigdVertrekspoor(gewijzigdVertrekspoor)
                .build();

    }

    private int getReistijdInMinuten(String time) {
        int seperator = time.indexOf(':');
        if (seperator == -1) {
            return Integer.parseInt(time);
        }
        int hour = Integer.parseInt(time.substring(0, seperator));
        int minutes = Integer.parseInt(time.substring(seperator + 1));
        return hour * 60 + minutes;
    }
}
