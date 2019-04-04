package nl.pvanassen.ns.model.storingen;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * For the exact meaning of all fields, see <a
 * href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">documentatie storingen en
 * werkzaamheden</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Storing implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String traject;
    private final String periode;
    private final String reden;
    private final String advies;
    private final String bericht;
    private final Date datum;
}
