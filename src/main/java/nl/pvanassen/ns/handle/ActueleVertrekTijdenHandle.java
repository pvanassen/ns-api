package nl.pvanassen.ns.handle;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;
import nl.pvanassen.ns.xml.Xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActueleVertrekTijdenHandle implements Handle<ActueleVertrekTijden> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ActueleVertrekTijden getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
        try {
            List<VertrekkendeTrein> vertrekkendeTreinen = new LinkedList<VertrekkendeTrein>();
            Xml xml = new Xml(stream, "ActueleVertrekTijden");
            for (Xml vertrekkendeTreinXml : xml.children("VertrekkendeTrein")) {
                int ritNummer = Integer.parseInt(vertrekkendeTreinXml.child("RitNummer").content());
                Date vertrekTijd = format.parse(vertrekkendeTreinXml.child("VertrekTijd").content());
                String vertrekVrrtraging = vertrekkendeTreinXml.child("VertrekVertraging").content();
                String vertrekVrrtragingTekst = vertrekkendeTreinXml.child("VertrekVertragingTekst").content();
                String eindBestemming = vertrekkendeTreinXml.child("EindBestemming").content();
                String treinSoort = vertrekkendeTreinXml.child("TreinSoort").content();
                String routeTekst = vertrekkendeTreinXml.child("RouteTekst").content();
                String vervoerder = vertrekkendeTreinXml.child("Vervoerder").content();
                int vertrekSpoor = Integer.parseInt(vertrekkendeTreinXml.child("VertrekSpoor").content());
                boolean vertrekSpoorWijziging = Boolean.valueOf(vertrekkendeTreinXml.child("VertrekSpoor").string("wijziging"));
//                VertrekkendeTrein vertrekkendeTrein = new VertrekkendeTrein(ritNummer, vertrekTijd, vertrekVertraging, vertrekVertragingTekst, eindBestemming, treinSoort, routeTekst, vervoerder, vertrekSpoor, gewijzigdVertrekspoor, reisTip, opmerkingen);
            }
        }
        catch (ParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
        // TODO Auto-generated method stub
        return null;
    }

}
