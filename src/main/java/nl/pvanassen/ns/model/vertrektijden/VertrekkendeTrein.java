package nl.pvanassen.ns.model.vertrektijden;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-actuele-vertrektijden
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

    /**
     * Constructor for vertrekkende trein (departing train)
     * 
     * @param ritNummer
     *            Ritnummer, number of the journey
     * @param vertrekTijd
     *            Vertrek tijd, departure time
     * @param vertrekVertraging
     * @param vertrekVertragingTekst
     * @param eindBestemming
     * @param treinSoort
     * @param routeTekst
     * @param vervoerder
     * @param vertrekSpoor
     * @param gewijzigdVertrekspoor
     * @param reisTip
     * @param opmerkingen
     */
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

    public int getRitNummer() {
        return ritNummer;
    }

    public Date getVertrekTijd() {
        return vertrekTijd;
    }

    public String getVertrekVertraging() {
        return vertrekVertraging;
    }

    public String getVertrekVertragingTekst() {
        return vertrekVertragingTekst;
    }

    public String getEindBestemming() {
        return eindBestemming;
    }

    public String getTreinSoort() {
        return treinSoort;
    }

    public String getRouteTekst() {
        return routeTekst;
    }

    public String getVervoerder() {
        return vervoerder;
    }

    public String getVertrekSpoor() {
        return vertrekSpoor;
    }

    public boolean isGewijzigdVertrekspoor() {
        return gewijzigdVertrekspoor;
    }

    public String getReisTip() {
        return reisTip;
    }

    public List<String> getOpmerkingen() {
        return opmerkingen;
    }

    public int getVertrekVertragingMinuten() {
        return vertrekVertragingMinuten;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (eindBestemming == null ? 0 : eindBestemming.hashCode());
        result = prime * result + (gewijzigdVertrekspoor ? 1231 : 1237);
        result = prime * result + (opmerkingen == null ? 0 : opmerkingen.hashCode());
        result = prime * result + (reisTip == null ? 0 : reisTip.hashCode());
        result = prime * result + ritNummer;
        result = prime * result + (routeTekst == null ? 0 : routeTekst.hashCode());
        result = prime * result + (treinSoort == null ? 0 : treinSoort.hashCode());
        result = prime * result + (vertrekSpoor == null ? 0 : vertrekSpoor.hashCode());
        result = prime * result + (vertrekTijd == null ? 0 : vertrekTijd.hashCode());
        result = prime * result + (vertrekVertraging == null ? 0 : vertrekVertraging.hashCode());
        result = prime * result + vertrekVertragingMinuten;
        result = prime * result + (vertrekVertragingTekst == null ? 0 : vertrekVertragingTekst.hashCode());
        result = prime * result + (vervoerder == null ? 0 : vervoerder.hashCode());
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
        if (!(obj instanceof VertrekkendeTrein)) {
            return false;
        }
        VertrekkendeTrein other = (VertrekkendeTrein) obj;
        if (eindBestemming == null) {
            if (other.eindBestemming != null) {
                return false;
            }
        }
        else if (!eindBestemming.equals(other.eindBestemming)) {
            return false;
        }
        if (gewijzigdVertrekspoor != other.gewijzigdVertrekspoor) {
            return false;
        }
        if (opmerkingen == null) {
            if (other.opmerkingen != null) {
                return false;
            }
        }
        else if (!opmerkingen.equals(other.opmerkingen)) {
            return false;
        }
        if (reisTip == null) {
            if (other.reisTip != null) {
                return false;
            }
        }
        else if (!reisTip.equals(other.reisTip)) {
            return false;
        }
        if (ritNummer != other.ritNummer) {
            return false;
        }
        if (routeTekst == null) {
            if (other.routeTekst != null) {
                return false;
            }
        }
        else if (!routeTekst.equals(other.routeTekst)) {
            return false;
        }
        if (treinSoort == null) {
            if (other.treinSoort != null) {
                return false;
            }
        }
        else if (!treinSoort.equals(other.treinSoort)) {
            return false;
        }
        if (vertrekSpoor == null) {
            if (other.vertrekSpoor != null) {
                return false;
            }
        }
        else if (!vertrekSpoor.equals(other.vertrekSpoor)) {
            return false;
        }
        if (vertrekTijd == null) {
            if (other.vertrekTijd != null) {
                return false;
            }
        }
        else if (!vertrekTijd.equals(other.vertrekTijd)) {
            return false;
        }
        if (vertrekVertraging == null) {
            if (other.vertrekVertraging != null) {
                return false;
            }
        }
        else if (!vertrekVertraging.equals(other.vertrekVertraging)) {
            return false;
        }
        if (vertrekVertragingMinuten != other.vertrekVertragingMinuten) {
            return false;
        }
        if (vertrekVertragingTekst == null) {
            if (other.vertrekVertragingTekst != null) {
                return false;
            }
        }
        else if (!vertrekVertragingTekst.equals(other.vertrekVertragingTekst)) {
            return false;
        }
        if (vervoerder == null) {
            if (other.vervoerder != null) {
                return false;
            }
        }
        else if (!vervoerder.equals(other.vervoerder)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VertrekkendeTrein [ritNummer=" + ritNummer + ", vertrekTijd=" + vertrekTijd + ", vertrekVertraging="
                + vertrekVertraging + ", vertrekVertragingMinuten=" + vertrekVertragingMinuten
                + ", vertrekVertragingTekst=" + vertrekVertragingTekst + ", eindBestemming=" + eindBestemming
                + ", treinSoort=" + treinSoort + ", routeTekst=" + routeTekst + ", vervoerder=" + vervoerder
                + ", vertrekSpoor=" + vertrekSpoor + ", gewijzigdVertrekspoor=" + gewijzigdVertrekspoor + ", reisTip="
                + reisTip + ", opmerkingen=" + opmerkingen + "]";
    }

}
