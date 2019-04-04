package nl.pvanassen.ns.model.vertrektijden;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Departing train object. For an exact explaination of all fields, please see For more information see <a
 * href="http://www.ns.nl/reisinformatie/ns-api/documentatie-actuele-vertrektijden.html">documentatie actuele vertrektijden</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VertrekkendeTrein implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private final Collection<String> opmerkingen;
}
