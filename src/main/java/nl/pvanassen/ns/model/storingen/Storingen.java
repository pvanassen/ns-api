package nl.pvanassen.ns.model.storingen;

import java.util.Collections;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden
 * 
 * @author Paul van Assen
 * 
 */
public class Storingen {

    private final List<Storing> ongeplandeStoringen;

    private final List<Storing> geplandeStoringen;

    Storingen(List<Storing> ongeplandeStoringen, List<Storing> geplandeStoringen) {
        this.ongeplandeStoringen = Collections.unmodifiableList(ongeplandeStoringen);
        this.geplandeStoringen = Collections.unmodifiableList(geplandeStoringen);
    }

    public List<Storing> getOngeplandeStoringen() {
        return ongeplandeStoringen;
    }

    public List<Storing> getGeplandeStoringen() {
        return geplandeStoringen;
    }
}
