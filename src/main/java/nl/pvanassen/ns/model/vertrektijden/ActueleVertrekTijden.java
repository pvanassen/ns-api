package nl.pvanassen.ns.model.vertrektijden;

import java.util.Collections;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden
 * 
 * @author Paul van Assen
 * 
 */
public class ActueleVertrekTijden {

    private final List<VertrekkendeTrein> vertrekkendeTreinen;

    public ActueleVertrekTijden(List<VertrekkendeTrein> vertrekkendeTreinen) {
        this.vertrekkendeTreinen = Collections.unmodifiableList(vertrekkendeTreinen);
    }

    public List<VertrekkendeTrein> getVertrekkendeTreinen() {
        return vertrekkendeTreinen;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (vertrekkendeTreinen == null ? 0 : vertrekkendeTreinen.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ActueleVertrekTijden)) {
            return false;
        }
        ActueleVertrekTijden other = (ActueleVertrekTijden) obj;
        if (vertrekkendeTreinen == null) {
            if (other.vertrekkendeTreinen != null) {
                return false;
            }
        }
        else if (!vertrekkendeTreinen.equals(other.vertrekkendeTreinen)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ActueleVertrekTijden [vertrekkendeTreinen=" + vertrekkendeTreinen + "]";
    }

}
