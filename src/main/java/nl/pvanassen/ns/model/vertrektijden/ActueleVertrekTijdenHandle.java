package nl.pvanassen.ns.model.vertrektijden;

import static java.lang.Boolean.parseBoolean;
import static java.util.Collections.unmodifiableCollection;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import nl.pvanassen.ns.NsApi;
import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

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
    @Override
    public VertrekkendeTreinen getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        try {
            List<VertrekkendeTrein> vertrekkendeTreinen = new LinkedList<>();
            Xml xml = Xml.getXml(stream, "ActueleVertrekTijden");
            for (Xml vertrekkendeTreinXml : xml.children("VertrekkendeTrein")) {
                int ritNummer = Integer.parseInt(vertrekkendeTreinXml.child("RitNummer").content());
                Date vertrekTijd = format.parse(vertrekkendeTreinXml.child("VertrekTijd").content());
                String vertrekVertraging = vertrekkendeTreinXml.child("VertrekVertraging").content();
                int vertrekVertragingMinuten = 0;
                if (vertrekVertraging != null && !vertrekVertraging.isEmpty()) {
                    try {
                        vertrekVertragingMinuten = Integer.parseInt(vertrekVertraging.replace("PT", "")
                                .replace("M", ""));
                    }
                    catch (NumberFormatException e) {
                        logger.warn("Error parsing vertrek vertraging minuten into minutes", e);
                    }
                }
                String vertrekVertragingTekst = vertrekkendeTreinXml.child("VertrekVertragingTekst").content();
                String eindBestemming = vertrekkendeTreinXml.child("EindBestemming").content();
                String treinSoort = vertrekkendeTreinXml.child("TreinSoort").content();
                String routeTekst = vertrekkendeTreinXml.child("RouteTekst").content();
                String vervoerder = vertrekkendeTreinXml.child("Vervoerder").content();
                String vertrekSpoor = vertrekkendeTreinXml.child("VertrekSpoor").content();
                boolean gewijzigdVertrekspoor = parseBoolean(vertrekkendeTreinXml.child("VertrekSpoor").attr(
                        "wijziging"));
                List<String> opmerkingen = new LinkedList<>();
                for (Xml opm : vertrekkendeTreinXml.children("Opmerkingen")) {
                    opmerkingen.add(opm.child("Opmerking").content());
                }
                String reisTip = vertrekkendeTreinXml.child("ReisTip").content();
                vertrekkendeTreinen.add(
                        VertrekkendeTrein.builder()
                                .eindBestemming(eindBestemming)
                                .gewijzigdVertrekspoor(gewijzigdVertrekspoor)
                                .opmerkingen(unmodifiableCollection(opmerkingen))
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
                                .build());
            }
            return VertrekkendeTreinen.builder()
                    .vertrekkendeTreinen(vertrekkendeTreinen)
                    .build();
        }
        catch (ParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

}
