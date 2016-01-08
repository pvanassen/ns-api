package nl.pvanassen.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
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

    ReisMogelijkheid(List<Melding> meldingen, int aantalOverstappen, int geplandeReisTijdMinuten,
            int actueleReisTijdMinuten, String aankomstVertraging, boolean optimaal, Date geplandeVertrekTijd,
            Date actueleVertrekTijd, Date geplandeAankomstTijd, Date actueleAankomstTijd, String status,
            List<ReisDeel> reisDelen) {
        super();
        this.meldingen = Collections.unmodifiableList(meldingen);
        this.aantalOverstappen = aantalOverstappen;
        this.geplandeReisTijdMinuten = geplandeReisTijdMinuten;
        this.actueleReisTijdMinuten = actueleReisTijdMinuten;
        this.aankomstVertraging = aankomstVertraging;
        this.optimaal = optimaal;
        this.geplandeVertrekTijd = geplandeVertrekTijd;
        this.actueleVertrekTijd = actueleVertrekTijd;
        this.geplandeAankomstTijd = geplandeAankomstTijd;
        this.actueleAankomstTijd = actueleAankomstTijd;
        this.status = status;
        this.reisDelen = Collections.unmodifiableList(reisDelen);
    }

    /**
     * @return A list of 'meldingen', or an empty list if none found
     */
    public List<Melding> getMeldingen() {
        return meldingen;
    }

    /**
     * @return Number of transfers
     */
    public int getAantalOverstappen() {
        return aantalOverstappen;
    }

    /**
     * @return Trip time in minutes according to the day-planning
     */
    public int getGeplandeReisTijdMinuten() {
        return geplandeReisTijdMinuten;
    }

    /**
     * @return Trip time in minutes according to the most current estimations
     */
    public int getActueleReisTijdMinuten() {
        return actueleReisTijdMinuten;
    }

    /**
     * @return Textual representation of the delay time on arrival
     */
    public String getAankomstVertraging() {
        return aankomstVertraging;
    }

    /**
     * @return False if this trip is not the most optimal one.
     */
    public boolean isOptimaal() {
        return optimaal;
    }

    /**
     * @return Planned time of departure
     */
    public Date getGeplandeVertrekTijd() {
        if (geplandeVertrekTijd == null) {
            return null;
        }
        return (Date)geplandeVertrekTijd.clone();
    }

    /**
     * @return Actual time of departure according to the most current estimations
     */
    public Date getActueleVertrekTijd() {
        if (actueleVertrekTijd == null) {
            return null;
        }
        return (Date)actueleVertrekTijd.clone();
    }

    /**
     * @return Planned time of arrival according to the day-planning
     */
    public Date getGeplandeAankomstTijd() {
        if (geplandeAankomstTijd == null) {
            return null;
        }
        return (Date)geplandeAankomstTijd.clone();
    }

    /**
     * @return Actual time of arrival according to the most current estimations
     */
    public Date getActueleAankomstTijd() {
        if (actueleAankomstTijd == null) {
            return null;
        }
        return (Date)actueleAankomstTijd.clone();
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
     * @return All travel legs
     */
    public List<ReisDeel> getReisDelen() {
        return reisDelen;
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
