package nl.pvanassen.ns.model.prijzen;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

import org.jetbrains.annotations.NotNull;

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
    @NotNull
    @Override
    public Prijzen getModel(@NotNull final InputStream stream) {
        final Xml xml = Xml.getXml(stream, "VervoerderKeuzes");

        final Map<String, VervoerderKeuze> vervoerderKeuzes = xml.children("VervoerderKeuze")
                .stream()
                .map(this::getVervoerderKeuze)
                .collect(Collectors.toMap(VervoerderKeuze::getNaam, Function.identity()));

        return Prijzen.builder().vervoerderKeuzes(unmodifiableMap(vervoerderKeuzes)).build();
    }

    private VervoerderKeuze getVervoerderKeuze(Xml vervoerderKeuze) {
        final String vervoerderKeuzeNaam = vervoerderKeuze.attr("naam");
        final int tariefEenheden = Integer.parseInt(vervoerderKeuze.child("Tariefeenheden").content());
        final Map<String, ReisType> reisTypes = vervoerderKeuze.children("ReisType")
                .stream()
                .map(this::getReisType)
                .collect(Collectors.toMap(ReisType::getNaam, Function.identity()));

        return VervoerderKeuze.builder()
                        .naam(vervoerderKeuzeNaam)
                        .reisTypes(unmodifiableMap(reisTypes))
                        .tariefEenheden(tariefEenheden)
                        .build();

    }

    private ReisType getReisType(Xml reisTypeXml) {
        final String reisTypeNaam = reisTypeXml.attr("name");
        final Map<Integer, ReisKlasse> reisKlassen = reisTypeXml.children("ReisKlasse")
                .stream()
                .map(this::getReisKlasse)
                .collect(Collectors.toMap(ReisKlasse::getKlasse, Function.identity()));

        return ReisType.builder()
                .naam(reisTypeNaam)
                .reisKlassen(unmodifiableMap(reisKlassen))
                .build();
    }

    private ReisKlasse getReisKlasse(Xml reisKlasseXml) {
        final int klasse = Integer.parseInt(reisKlasseXml.attr("klasse"));

        final List<Prijsdeel> prijsdelen = reisKlasseXml.children("Prijsdeel")
                .stream()
                .map(prijsdeelXml -> Prijsdeel.builder()
                        .naar(prijsdeelXml.attr("naar"))
                        .prijs(new BigDecimal(prijsdeelXml.attr("prijs")))
                        .van(prijsdeelXml.attr("van"))
                        .vervoerder(prijsdeelXml.attr("vervoerder"))
                        .build())
                .collect(Collectors.toList());

        final BigDecimal totaal = new BigDecimal(reisKlasseXml.child("Totaal").content());
        final Map<String, BigDecimal> kortingprijzen = reisKlasseXml.child("Korting")
                .children("Kortingsprijs")
                .stream()
                .collect(Collectors.toMap(kortingsPrijsXml -> kortingsPrijsXml.attr("name"), kortingsPrijsXml -> new BigDecimal(kortingsPrijsXml.attr("prijs"))));

        return ReisKlasse.builder()
                .klasse(klasse)
                .korting(unmodifiableMap(kortingprijzen))
                .prijsdeel(unmodifiableList(prijsdelen))
                .totaal(totaal)
                .build();
    }
}
