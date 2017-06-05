package nl.pvanassen.ns.model.storingen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * For the exact meaning of all fields, see <a
 * href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">documentatie storingen en
 * werkzaamheden</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Storing {
    private final String id;
    private final String traject;
    private final String periode;
    private final String reden;
    private final String advies;
    private final String bericht;
    private final Date datum;
}
