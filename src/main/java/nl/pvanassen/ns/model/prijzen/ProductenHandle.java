package nl.pvanassen.ns.model.prijzen;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.xml.Xml;

public class ProductenHandle implements Handle<Producten> {

    @Override
    public Producten getModel(InputStream stream) {
        List<Product> producten = new LinkedList<Product>();
        Xml xml = Xml.getXml(stream, "Producten");
        int tariefEenheden = Integer.parseInt(xml.child("Tariefeenheden").content());
        for (Xml productXml : xml.children("Product ")) {
            String naam = productXml.string("naam");
            List<Prijs> prijzen = new ArrayList<Prijs>(productXml.children("Prijs").size());
            for (Xml prijsXml : productXml.children("Prijs")) {
                String korting = prijsXml.string("korting");
                int klasse = Integer.parseInt(prijsXml.string("klasse"));
                int prijs = Integer.parseInt(prijsXml.content().replace(",", ""));
                prijzen.add(new Prijs(korting, klasse, prijs));
            }
            producten.add(new Product(naam, prijzen));
        }
        return new Producten(tariefEenheden, producten);
    }
}
