package nl.pvanassen.ns.model.reisadvies;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import nl.pvanassen.ns.NsApi;
import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

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
    @Override
    public ReisMogelijkheden getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        try {
            Xml xml = Xml.getXml(stream, "ReisMogelijkheden");
            List<ReisMogelijkheid> reisMogelijkheden = new ArrayList<ReisMogelijkheid>(xml.children("ReisMogelijkheid")
                    .size());
            for (Xml reisMogelijkheidXml : xml.children("ReisMogelijkheid")) {
                List<Melding> meldingen = new LinkedList<Melding>();
                if (reisMogelijkheidXml.isPresent("Melding")) {
                    for (Xml meldingXml : reisMogelijkheidXml.children("Melding")) {
                        String id = meldingXml.child("Id").content();
                        boolean ernstig = Boolean.parseBoolean(meldingXml.child("Ernstig").content());
                        String text = meldingXml.child("Text").content();
                        meldingen.add(new Melding(id, ernstig, text));
                    }
                }
                int aantalOverstappen = Integer.parseInt(reisMogelijkheidXml.child("AantalOverstappen").content());
                int geplandeReisTijdMinuten = getReistijdInMinuten(reisMogelijkheidXml.child("GeplandeReisTijd")
                        .content());
                int actueleReisTijdMinuten = getReistijdInMinuten(reisMogelijkheidXml.child("ActueleReisTijd")
                        .content());
                boolean optimaal = Boolean.parseBoolean(reisMogelijkheidXml.child("Optimaal").content());
                Date geplandeVertrekTijd = format.parse(reisMogelijkheidXml.child("GeplandeVertrekTijd").content());
                Date actueleVertrekTijd = format.parse(reisMogelijkheidXml.child("ActueleVertrekTijd").content());
                Date geplandeAankomstTijd = format.parse(reisMogelijkheidXml.child("GeplandeAankomstTijd").content());
                Date actueleAankomstTijd = format.parse(reisMogelijkheidXml.child("ActueleAankomstTijd").content());
                String aankomstVertraging = reisMogelijkheidXml.child("AankomstVertraging").content();
                String status = reisMogelijkheidXml.child("Status").content();
                List<ReisDeel> reisDelen = new ArrayList<ReisDeel>(reisMogelijkheidXml.children("ReisDeel").size());
                for (Xml reisDeelXml : reisMogelijkheidXml.children("ReisDeel")) {
                    String reisSoort = reisDeelXml.attr("reisSoort");
                    String vervoerder = reisDeelXml.child("Vervoerder").content();
                    String vervoerType = reisDeelXml.child("VervoerType").content();
                    int ritNummer = Integer.parseInt(reisDeelXml.child("RitNummer").content());
                    String statusReisdeel = reisDeelXml.child("Status").content();
                    String geplandeStoringId = reisDeelXml.child("GeplandeStoringId").content();
                    String ongeplandeStoringId = reisDeelXml.child("OngeplandeStoringId").content();
                    List<ReisStop> reisStops = new ArrayList<ReisStop>(reisDeelXml.children("ReisStop").size());
                    for (Xml reisStopXml : reisDeelXml.children("ReisStop")) {
                        String naam = reisStopXml.child("Naam").content();
                        Date tijd = format.parse(reisStopXml.child("Tijd").content());
                        String spoor = reisStopXml.child("Spoor").content();
                        boolean gewijzigdVertrekspoor = Boolean.parseBoolean(reisStopXml.child("Spoor").attr(
                                "wijziging"));
                        reisStops.add(new ReisStop(naam, tijd, spoor, gewijzigdVertrekspoor));
                    }
                    List<String> reisDetails = new ArrayList<String>(reisDeelXml.child("Reisdetails")
                            .children("Reisdetail").size());
                    for (Xml reisDetailXml : reisDeelXml.child("Reisdetails").children("Reisdetail")) {
                        reisDetails.add(reisDetailXml.content());
                    }
                    reisDelen.add(new ReisDeel(reisSoort, vervoerder, vervoerType, ritNummer, statusReisdeel,
                            reisStops, ongeplandeStoringId, geplandeStoringId, reisDetails));
                }
                reisMogelijkheden.add(new ReisMogelijkheid(meldingen, aantalOverstappen, geplandeReisTijdMinuten,
                        actueleReisTijdMinuten, aankomstVertraging, optimaal, geplandeVertrekTijd, actueleVertrekTijd,
                        geplandeAankomstTijd, actueleAankomstTijd, status, reisDelen));
            }
            return new ReisMogelijkheden(reisMogelijkheden);
        }
        catch (ParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

    private int getReistijdInMinuten(String time) {
        int seperator = time.indexOf(':');
        if (seperator == -1) {
            return Integer.parseInt(time);
        }
        int hour = Integer.parseInt(time.substring(0, seperator));
        int minutes = Integer.parseInt(time.substring(seperator + 1));
        return (hour * 60) + minutes;
    }
}
