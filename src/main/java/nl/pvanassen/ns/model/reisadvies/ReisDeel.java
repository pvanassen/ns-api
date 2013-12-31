package nl.pvanassen.ns.model.reisadvies;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * See <a href="http://www.ns.nl/api/api#api-documentatie-reisadviezen">documentatie reisadviezen</a> for the exact
 * meaning of all fields
 * 
 * @author Paul van Assen
 * 
 */
public class ReisDeel {

    private final String reisSoort;

    private final String vervoerder;

    private final String vervoerType;

    private final int ritNummer;

    private final String status;

    private final List<ReisStop> reisStops;

    private final String ongeplandeStoringId;

    private final String geplandeStoringId;

    private final List<String> reisDetails;

    ReisDeel(String reisSoort, String vervoerder, String vervoerType, int ritNummer, String status,
            List<ReisStop> reisStops, String ongeplandeStoringId, String geplandeStoringId, List<String> reisDetails) {
        super();
        this.reisSoort = reisSoort;
        this.vervoerder = vervoerder;
        this.vervoerType = vervoerType;
        this.ritNummer = ritNummer;
        this.status = status;
        this.reisStops = Collections.unmodifiableList(reisStops);
        this.ongeplandeStoringId = ongeplandeStoringId;
        this.geplandeStoringId = geplandeStoringId;
        this.reisDetails = Collections.unmodifiableList(reisDetails);
    }

    /**
     * @return Type of travel. Usually 'TRAIN' but this can contain other values
     */
    public String getReisSoort() {
        return reisSoort;
    }

    /**
     * @return Transportation company. This can be NS but other companies are also possible
     */
    public String getVervoerder() {
        return vervoerder;
    }

    /**
     * @return Type of transportation. The values include but are not limited to Intercity and Sprinter
     */
    public String getVervoerType() {
        return vervoerType;
    }

    /**
     * @return Number that uniquely identifies this ride.
     */
    public int getRitNummer() {
        return ritNummer;
    }

    /**
     * The status of a ride can consit of the following values:
     * <ul>
     * <li>VOLGENS-PLAN: As planned</li>
     * <li>GEANNULEERD: Canceled</li>
     * <li>GEWIJZIGD: Small change in the schedule made on the day itself</li>
     * <li>OVERSTAP-NIET-MOGELIJK: No transfer possibke</li>
     * <li>VERTRAAGD: Delayed</li>
     * <li>NIEUW: Extra train</li>
     * </ul>
     * 
     * @return Status of this ride.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return List of stops
     */
    public List<ReisStop> getReisStops() {
        return reisStops;
    }

    /**
     * @return Unplanned disruption ids or null if none
     */
    public String getOngeplandeStoringId() {
        return ongeplandeStoringId;
    }

    /**
     * @return Planned disruption ids or null if none
     */
    public String getGeplandeStoringId() {
        return geplandeStoringId;
    }

    /**
     * @return Any extra details about this trip. Mostly applies to foreign trains or rides which require special
     *         tickets
     */
    public List<String> getReisDetails() {
        return reisDetails;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
