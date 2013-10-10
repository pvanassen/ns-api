package nl.pvanassen.ns.handle;

import java.io.InputStream;

import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;
import nl.pvanassen.ns.xml.Xml;

public class ActueleVertrekTijdenHandle implements Handle<ActueleVertrekTijden> {

	@Override
	public ActueleVertrekTijden getModel(InputStream stream) {
		Xml xml = new Xml(stream, "ActueleVertrekTijden");
		for (Xml vertrekkendeTrein : xml.children("VertrekkendeTrein")) {
			vertrekkendeTrein.child("RitNummer").content();
			vertrekkendeTrein.child("VertrekTijd").content();
			vertrekkendeTrein.child("VertrekVertraging").content();
			vertrekkendeTrein.child("VertrekVertragingTekst").content();
			vertrekkendeTrein.child("EindBestemming").content();
			vertrekkendeTrein.child("TreinSoort").content();
			vertrekkendeTrein.child("RouteTekst").content();
			vertrekkendeTrein.child("Vervoerder").content();
			vertrekkendeTrein.child("VertrekSpoor").content();
			vertrekkendeTrein.child("VertrekSpoor").string("wijziging");
		}
		// TODO Auto-generated method stub
		return null;
	}

}
