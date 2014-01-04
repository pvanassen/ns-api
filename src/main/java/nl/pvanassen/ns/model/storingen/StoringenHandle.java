package nl.pvanassen.ns.model.storingen;

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
    @Override
    public Storingen getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        try {
            List<Storing> geplandeStoringen = new LinkedList<Storing>();
            List<Storing> ongeplandeStoringen = new LinkedList<Storing>();

            Xml xml = Xml.getXml(stream, "Storingen");
            if (xml.isPresent("Ongepland")) {
                Xml ongeplandeStoringenXml = xml.child("Ongepland");
                for (Xml storingXml : ongeplandeStoringenXml.children("Storing")) {
                    ongeplandeStoringen.add(getStoring(storingXml, format));
                }
            }
            if (xml.isPresent("Gepland")) {
                Xml geplandeStoringenXml = xml.child("Gepland");
                for (Xml storingXml : geplandeStoringenXml.children("Storing")) {
                    geplandeStoringen.add(getStoring(storingXml, format));
                }
            }
            return new Storingen(ongeplandeStoringen, geplandeStoringen);
        }
        catch (ParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

    private Storing getStoring(Xml storingXml, SimpleDateFormat format) throws ParseException {
        String id = storingXml.child("id").content();
        String traject = storingXml.child("Traject").content();
        String periode = storingXml.child("Periode").content();
        String reden = storingXml.child("Reden").content();
        String advies = storingXml.child("Advies").content();
        String bericht = storingXml.child("Bericht").content();
        Date datum = null;
        if (storingXml.isPresent("Datum")) {
            datum = format.parse(storingXml.child("Datum").content());
        }
        return new Storing(id, traject, periode, reden, advies, bericht, datum);
    }

}
