package nl.pvanassen.ns.model.reisadvies;

import java.util.Date;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisStop {

    private String naam;

    private Date tijd;

    private int spoor;

    private boolean gewijzigdVertrekspoor;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getTijd() {
        return tijd;
    }

    public void setTijd(Date tijd) {
        this.tijd = tijd;
    }

    public int getSpoor() {
        return spoor;
    }

    public void setSpoor(int spoor) {
        this.spoor = spoor;
    }

    public boolean isGewijzigdVertrekspoor() {
        return gewijzigdVertrekspoor;
    }

    public void setGewijzigdVertrekspoor(boolean gewijzigdVertrekspoor) {
        this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
    }

}
