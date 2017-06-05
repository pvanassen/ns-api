package nl.pvanassen.ns.model.vertrektijden;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Departing train object. For an exact explaination of all fields, please see For more information see <a
 * href="http://www.ns.nl/reisinformatie/ns-api/documentatie-actuele-vertrektijden.html">documentatie actuele vertrektijden</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class VertrekkendeTrein {
    private final int ritNummer;
    private final Date vertrekTijd;
    private final String vertrekVertraging;
    private final int vertrekVertragingMinuten;
    private final String vertrekVertragingTekst;
    private final String eindBestemming;
    private final String treinSoort;
    private final String routeTekst;
    private final String vervoerder;
    private final String vertrekSpoor;
    private final boolean gewijzigdVertrekspoor;
    private final String reisTip;
    private final List<String> opmerkingen;
}
