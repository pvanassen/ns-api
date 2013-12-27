package nl.pvanassen.ns.model.reisadvies;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
public class ReisMogelijkheid {

    private int aantalOverstappen;

    private int geplandeReisTijdMinuten;

    private int actueleReisTijdMinuten;

    private String aankomstVertraging;

    private boolean optimaal;

    private Date geplandeVertrekTijd;

    private Date actueleVertrekTijd;

    private Date geplandeAankomstTijd;

    private Date actueleAankomstTijd;

    private String status;

    private final List<ReisDeel> reisDelen = new LinkedList<ReisDeel>();

    public int getAantalOverstappen() {
        return aantalOverstappen;
    }

    public void setAantalOverstappen(int aantalOverstappen) {
        this.aantalOverstappen = aantalOverstappen;
    }

    public int getGeplandeReisTijdMinuten() {
        return geplandeReisTijdMinuten;
    }

    public void setGeplandeReisTijdMinuten(int geplandeReisTijdMinuten) {
        this.geplandeReisTijdMinuten = geplandeReisTijdMinuten;
    }

    public int getActueleReisTijdMinuten() {
        return actueleReisTijdMinuten;
    }

    public void setActueleReisTijdMinuten(int actueleReisTijdMinuten) {
        this.actueleReisTijdMinuten = actueleReisTijdMinuten;
    }

    public boolean isOptimaal() {
        return optimaal;
    }

    public void setOptimaal(boolean optimaal) {
        this.optimaal = optimaal;
    }

    public Date getGeplandeVertrekTijd() {
        return geplandeVertrekTijd;
    }

    public void setGeplandeVertrekTijd(Date geplandeVertrekTijd) {
        this.geplandeVertrekTijd = geplandeVertrekTijd;
    }

    public Date getActueleVertrekTijd() {
        return actueleVertrekTijd;
    }

    public void setActueleVertrekTijd(Date actueleVertrekTijd) {
        this.actueleVertrekTijd = actueleVertrekTijd;
    }

    public Date getGeplandeAankomstTijd() {
        return geplandeAankomstTijd;
    }

    public void setGeplandeAankomstTijd(Date geplandeAankomstTijd) {
        this.geplandeAankomstTijd = geplandeAankomstTijd;
    }

    public Date getActueleAankomstTijd() {
        return actueleAankomstTijd;
    }

    public void setActueleAankomstTijd(Date actueleAankomstTijd) {
        this.actueleAankomstTijd = actueleAankomstTijd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ReisDeel> getReisDelen() {
        return reisDelen;
    }

    public String getAankomstVertraging() {
        return aankomstVertraging;
    }

    public void setAankomstVertraging(String aankomstVertraging) {
        this.aankomstVertraging = aankomstVertraging;
    }
}
