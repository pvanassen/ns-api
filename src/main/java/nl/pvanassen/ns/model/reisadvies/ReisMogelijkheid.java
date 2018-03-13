package nl.pvanassen.ns.model.reisadvies;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ReisMogelijkheid implements Serializable {
    private final List<Melding> meldingen;
    private final int aantalOverstappen;
    private final int geplandeReisTijdMinuten;
    private final int actueleReisTijdMinuten;
    private final String aankomstVertraging;
    private final boolean optimaal;
    private final Date geplandeVertrekTijd;
    private final Date actueleVertrekTijd;
    private final Date geplandeAankomstTijd;
    private final Date actueleAankomstTijd;
    private final String status;
    private final List<ReisDeel> reisDelen;
}
