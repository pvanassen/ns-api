package nl.pvanassen.ns.model.storingen;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden
 * 
 * @author Paul van Assen
 * 
 */
public class Storingen {

    private final List<Storing> ongeplandeStoringen = new LinkedList<Storing>();

    private final List<Storing> geplandeStoringen = new LinkedList<Storing>();

    public List<Storing> getOngeplandeStoringen() {
        return ongeplandeStoringen;
    }

    public List<Storing> getGeplandeStoringen() {
        return geplandeStoringen;
    }
}
