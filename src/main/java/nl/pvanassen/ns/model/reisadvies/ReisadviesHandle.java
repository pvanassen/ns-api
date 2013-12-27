package nl.pvanassen.ns.model.reisadvies;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.pvanassen.ns.NsApi;
import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReisadviesHandle implements Handle<ReisMogelijkheden> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ReisMogelijkheden getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        try {
            Xml xml = Xml.getXml(stream, "ReisMogelijkheden");
            List<ReisMogelijkheid> reisMogelijkheden = new ArrayList<ReisMogelijkheid>(xml.children("ReisMogelijkheid").size());
            for (Xml reisMovelijkheidXml : xml.children("ReisMogelijkheid")) {
                Melding melding = null;
                if (reisMovelijkheidXml.isPresent("Melding")) {
                    Xml meldingXml = reisMovelijkheidXml.child("Melding");
                    String id = meldingXml.child("Id").content();
                    boolean ernstig = Boolean.parseBoolean(meldingXml.child("Ernstig").content());
                    String text = meldingXml.child("Text").content();
                    melding = new Melding(id, ernstig, text);
                }
                int aantalOverstappen = Integer.parseInt(reisMovelijkheidXml.child("AantalOverstappen").content());
                int geplandeReisTijdMinuten = getReistijdInMinuten(reisMovelijkheidXml.child("GeplandeReisTijd").content());
                int actueleReisTijdMinuten = getReistijdInMinuten(reisMovelijkheidXml.child("ActueleReisTijd").content());
                boolean optimaal = Boolean.parseBoolean(reisMovelijkheidXml.child("Optimaal").content());
                Date geplandeVertrekTijd = format.parse(reisMovelijkheidXml.child("GeplandeVertrekTijd").content());
                Date actueleVertrekTijd = format.parse(reisMovelijkheidXml.child("ActueleVertrekTijd").content());
                Date geplandeAankomstTijd = format.parse(reisMovelijkheidXml.child("GeplandeAankomstTijd").content());
                Date actueleAankomstTijd = format.parse(reisMovelijkheidXml.child("ActueleAankomstTijd").content());
                String aankomstVertraging = reisMovelijkheidXml.child("AankomstVertraging").content();
                String status =  reisMovelijkheidXml.child("Status").content();
                List<ReisDeel> reisDelen = new ArrayList<ReisDeel>(reisMovelijkheidXml.children("ReisDeel").size());
                for (Xml reisDeelXml : reisMovelijkheidXml.children("ReisDeel")) {
                    String reisSoort = reisDeelXml.string("reisSoort");
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
                        boolean gewijzigdVertrekspoor = Boolean.parseBoolean(reisStopXml.child("Spoor").string("wijziging"));
                        reisStops.add(new ReisStop(naam, tijd, spoor, gewijzigdVertrekspoor));
                    }
                    List<String> reisDetails = new ArrayList<String>(reisDeelXml.child("Reisdetails").children("Reisdetail").size());
                    for (Xml reisDetailXml : reisDeelXml.child("Reisdetails").children("Reisdetail")) {
                        reisDetails.add(reisDetailXml.content());
                    }
                    reisDelen.add(new ReisDeel(reisSoort, vervoerder, vervoerType, ritNummer, statusReisdeel, reisStops, ongeplandeStoringId, geplandeStoringId, reisDetails));
                }
                reisMogelijkheden.add(new ReisMogelijkheid(melding, aantalOverstappen, geplandeReisTijdMinuten, actueleReisTijdMinuten, aankomstVertraging, optimaal, geplandeVertrekTijd, actueleVertrekTijd, geplandeAankomstTijd, actueleAankomstTijd, status, reisDelen));
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
