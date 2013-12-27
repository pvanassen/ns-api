package nl.pvanassen.ns.model.prijzen;

/**
 * http://www.ns.nl/api/api#api-documentatie-prijzen Prijs element
 * 
 * @author Paul van Assen
 * 
 */
public class Prijs {

    private String korting;

    private int klasse;

    private int prijs;

    public String getKorting() {
        return korting;
    }

    public void setKorting(String korting) {
        this.korting = korting;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    /**
     * @return Prijs in eurocenten
     */
    public int getPrijs() {
        return prijs;
    }

    /**
     * @param prijs
     *            Prijs in eurocenten
     */
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }
}
