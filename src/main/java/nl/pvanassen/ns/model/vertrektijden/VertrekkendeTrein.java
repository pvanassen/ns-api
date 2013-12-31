package nl.pvanassen.ns.model.vertrektijden;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Departing train object. For an exact explaination of all fields, please see For more information see <a href="http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden">documentatie actuele vertrektijden</a>
 * 
 * @author Paul van Assen
 * 
 */
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

    VertrekkendeTrein(int ritNummer, Date vertrekTijd, String vertrekVertraging, int vertrekVertragingMinuten,
            String vertrekVertragingTekst, String eindBestemming, String treinSoort, String routeTekst,
            String vervoerder, String vertrekSpoor, boolean gewijzigdVertrekspoor, String reisTip,
            List<String> opmerkingen) {
        super();
        this.ritNummer = ritNummer;
        this.vertrekTijd = vertrekTijd;
        this.vertrekVertraging = vertrekVertraging;
        this.vertrekVertragingMinuten = vertrekVertragingMinuten;
        this.vertrekVertragingTekst = vertrekVertragingTekst;
        this.eindBestemming = eindBestemming;
        this.treinSoort = treinSoort;
        this.routeTekst = routeTekst;
        this.vervoerder = vervoerder;
        this.vertrekSpoor = vertrekSpoor;
        this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
        this.reisTip = reisTip;
        this.opmerkingen = Collections.unmodifiableList(opmerkingen);
    }

    /**
     * @return Identification number of a train
     */
    public int getRitNummer() {
        return ritNummer;
    }

    /**
     * @return Departure time of a train
     */
    public Date getVertrekTijd() {
        return vertrekTijd;
    }

    /**
     * @return If the train has a delay this is filled with the delay text as provided by the NS
     */
    public String getVertrekVertraging() {
        return vertrekVertraging;
    }
    
    /**
     * @return If the train has a delay this is filled the parsed 'vertrekVertraging', transformed into the amount of minutes a train is delayed
     */
    public int getVertrekVertragingMinuten() {
        return vertrekVertragingMinuten;
    }

    /**
     * @return Textual explanation of the delay
     */
    public String getVertrekVertragingTekst() {
        return vertrekVertragingTekst;
    }
    
    /**
     * @return Planned destination of the train, a station name 
     */
    public String getEindBestemming() {
        return eindBestemming;
    }

    /**
     * @return Sort of train including but not limited to Intercity or Sprinter
     */
    public String getTreinSoort() {
        return treinSoort;
    }

    /**
     * @return Short route text with a maximum of 4 stops. Usually a summary of the route
     */
    public String getRouteTekst() {
        return routeTekst;
    }

    /**
     * @return The company running the train, usually 'NS'
     */
    public String getVervoerder() {
        return vervoerder;
    }

    /**
     * @return Track/platform of departure
     */
    public String getVertrekSpoor() {
        return vertrekSpoor;
    }

    /**
     * @return True if the departure track/platform was changed compared to the planned track/platform
     */
    public boolean isGewijzigdVertrekspoor() {
        return gewijzigdVertrekspoor;
    }

    /**
     * @return Extra information about the train, for example, Intercity will stop at all stations
     */
    public String getReisTip() {
        return reisTip;
    }

    /**
     * @return List of remarks about the train. This can provide extra information on why a train is not departing on time. 
     */
    public List<String> getOpmerkingen() {
        return opmerkingen;
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
