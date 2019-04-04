package nl.pvanassen.ns.model.prijzen;

import static java.util.Collections.unmodifiableMap;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

/**
 * Handle for parsing 'producten' xml, as defined in <a
 * href="http://www.ns.nl/api/api#api-documentatie-prijzen">documentatie prijzen</a>
 * 
 * @author Paul van Assen
 * 
 */
public class PrijsHandle implements Handle<Prijzen> {

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.handle.Handle#getModel(java.io.InputStream)
     */
    @Override
    public Prijzen getModel(InputStream stream) {
        Map<String, VervoerderKeuze> vervoerderKeuzes = new HashMap<>();
        Xml xml = Xml.getXml(stream, "VervoerderKeuzes");
        for (Xml vervoerderKeuze : xml.children("VervoerderKeuze")) {
            String vervoerderKeuzeNaam = vervoerderKeuze.attr("naam");
            int tariefEenheden = Integer.parseInt(vervoerderKeuze.child("Tariefeenheden").content());
            Map<String, ReisType> reisTypes = new HashMap<>();
            for (Xml reisTypeXml : vervoerderKeuze.children("ReisType")) {
                String reisTypeNaam = reisTypeXml.attr("name");
                Map<Integer, ReisKlasse> reisKlassen = new HashMap<>();
                for (Xml reisKlasseXml : reisTypeXml.children("ReisKlasse")) {
                    int klasse = Integer.parseInt(reisKlasseXml.attr("klasse"));
                    //  <Prijsdeel vervoerder="NS" prijs="47.20" naar="GDM" van="RTD"/>
                    List<Prijsdeel> prijsdelen = new LinkedList<>();
                    for (Xml prijsdeelXml : reisKlasseXml.children("Prijsdeel")) {
                        prijsdelen.add(new Prijsdeel(prijsdeelXml.attr("vervoerder"), new BigDecimal(prijsdeelXml.attr("prijs")), prijsdeelXml.attr("van"), prijsdeelXml.attr("naar")));
                    }
                    BigDecimal totaal = new BigDecimal(reisKlasseXml.child("Totaal").content());
                    Map<String, BigDecimal> kortingprijzen = new HashMap<>();
                    for (Xml kortingsPrijsXml : reisKlasseXml.child("Korting").children("Kortingsprijs")) {
                        kortingprijzen.put(kortingsPrijsXml.attr("name"), new BigDecimal(kortingsPrijsXml.attr("prijs")));
                    }
                    reisKlassen.put(klasse, new ReisKlasse(klasse, prijsdelen, totaal, unmodifiableMap(kortingprijzen)));
                }
                reisTypes.put(reisTypeNaam, new ReisType(reisTypeNaam, unmodifiableMap(reisKlassen)));
            }
            vervoerderKeuzes.put(vervoerderKeuzeNaam, new VervoerderKeuze(vervoerderKeuzeNaam, tariefEenheden, unmodifiableMap(reisTypes)));
        }
        return new Prijzen(unmodifiableMap(vervoerderKeuzes));
    }
}
