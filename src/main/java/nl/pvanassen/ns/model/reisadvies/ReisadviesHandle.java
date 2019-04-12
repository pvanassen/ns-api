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
            final XmlResponse response = Response.getXml(stream, "ReisMogelijkheden");
            final List<ReisMogelijkheid> reisMogelijkheden = response.children("ReisMogelijkheid")
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

    private ReisMogelijkheid getReisMogelijkheid(final XmlResponse reisMogelijkheidResponse) {
        final List<Melding> meldingen = reisMogelijkheidResponse.children("Melding")
                .stream()
                .map(this::getMelding)
                .collect(Collectors.toList());

        final int aantalOverstappen = Integer.parseInt(reisMogelijkheidResponse.child("AantalOverstappen").content());
        final int geplandeReisTijdMinuten = getReistijdInMinuten(reisMogelijkheidResponse.child("GeplandeReisTijd")
                .content());
        final int actueleReisTijdMinuten = getReistijdInMinuten(reisMogelijkheidResponse.child("ActueleReisTijd")
                .content());

        final boolean optimaal = reisMogelijkheidResponse.childIfPresent("Optimaal")
                .map(xml -> parseBoolean(xml.content()))
                .orElse(TRUE);

        final LocalDateTime geplandeVertrekTijd = parse(reisMogelijkheidResponse.child("GeplandeVertrekTijd").content(), DATETIME_FORMATTER);
        final LocalDateTime actueleVertrekTijd = parse(reisMogelijkheidResponse.child("ActueleVertrekTijd").content(), DATETIME_FORMATTER);
        final LocalDateTime geplandeAankomstTijd = parse(reisMogelijkheidResponse.child("GeplandeAankomstTijd").content(), DATETIME_FORMATTER);
        final LocalDateTime actueleAankomstTijd = parse(reisMogelijkheidResponse.child("ActueleAankomstTijd").content(), DATETIME_FORMATTER);

        final String aankomstVertraging = reisMogelijkheidResponse.child("AankomstVertraging").content();
        final String status = reisMogelijkheidResponse.child("Status").content();

        final List<ReisDeel> reisDelen = reisMogelijkheidResponse.children("ReisDeel")
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

    private Melding getMelding(final Response meldingResponse) {
        final String id = meldingResponse.child("Id").content();
        final boolean ernstig = parseBoolean(meldingResponse.child("Ernstig").content());
        final String text = meldingResponse.child("Text").content();
        return Melding.builder()
                .id(id)
                .ernstig(ernstig)
                .text(text)
                .build();
    }

    private ReisDeel getReisdeel(XmlResponse reisDeelResponse) {
        final String reisSoort = reisDeelResponse.attr("reisSoort");
        final String vervoerder = reisDeelResponse.requiredChild("Vervoerder").content();
        final String vervoerType = reisDeelResponse.requiredChild("VervoerType").content();
        final int ritNummer = Integer.parseInt(reisDeelResponse.requiredChild("RitNummer").content());
        final String statusReisdeel = reisDeelResponse.requiredChild("Status").content();
        final String geplandeStoringId = reisDeelResponse.child("GeplandeStoringId").content();
        final String ongeplandeStoringId = reisDeelResponse.child("OngeplandeStoringId").content();
        final List<ReisStop> reisStops = reisDeelResponse.children("ReisStop")
                .stream()
                .map(this::getReisStop)
                .collect(Collectors.toList());

        final List<String> reisDetails = reisDeelResponse.child("Reisdetails").children("Reisdetail")
                .stream()
                .map(Response::content)
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

    private ReisStop getReisStop(final XmlResponse reisStopResponse) {
        final String naam = reisStopResponse.requiredChild("Naam").content();
        final LocalDateTime tijd = LocalDateTime.parse(reisStopResponse.requiredChild("Tijd").content(), DATETIME_FORMATTER);
        final String spoor = reisStopResponse.child("Spoor").content();
        final boolean gewijzigdVertrekspoor = ofNullable(spoor).map(ignored -> parseBoolean(reisStopResponse.child("Spoor").attr("wijziging"))).orElse(false);

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
