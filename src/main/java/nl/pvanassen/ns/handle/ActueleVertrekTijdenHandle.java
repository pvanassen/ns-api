package nl.pvanassen.ns.handle;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;
import nl.pvanassen.ns.xml.Xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActueleVertrekTijdenHandle implements Handle<ActueleVertrekTijden> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public ActueleVertrekTijden getModel(InputStream stream) {
		SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
		try {
			List<VertrekkendeTrein> vertrekkendeTreinen = new LinkedList<VertrekkendeTrein>();
			Xml xml = Xml.getXml(stream, "ActueleVertrekTijden");
			for (Xml vertrekkendeTreinXml : xml.children("VertrekkendeTrein")) {
				int ritNummer = Integer.parseInt(vertrekkendeTreinXml.child(
						"RitNummer").content());
				Date vertrekTijd = format.parse(vertrekkendeTreinXml.child(
						"VertrekTijd").content());
				String vertrekVertraging = vertrekkendeTreinXml.child(
						"VertrekVertraging").content();
				int vertrekVertragingMinuten = 0;
				if (vertrekVertraging != null && !vertrekVertraging.isEmpty()) {
					try {
						vertrekVertragingMinuten = Integer
								.parseInt(vertrekVertraging.replace("PT", "")
										.replace("M", ""));
					} catch (NumberFormatException e) {
						logger.warn(
								"Error parsing vertrek vertraging minuten into minutes",
								e);
					}
				}
				String vertrekVertragingTekst = vertrekkendeTreinXml.child(
						"VertrekVertragingTekst").content();
				String eindBestemming = vertrekkendeTreinXml.child(
						"EindBestemming").content();
				String treinSoort = vertrekkendeTreinXml.child("TreinSoort")
						.content();
				String routeTekst = vertrekkendeTreinXml.child("RouteTekst")
						.content();
				String vervoerder = vertrekkendeTreinXml.child("Vervoerder")
						.content();
				String vertrekSpoor = vertrekkendeTreinXml
						.child("VertrekSpoor").content();
				boolean gewijzigdVertrekspoor = Boolean
						.valueOf(vertrekkendeTreinXml.child("VertrekSpoor")
								.string("wijziging"));
				List<String> opmerkingen = new LinkedList<String>();
				for (Xml opm : vertrekkendeTreinXml.children("Opmerkingen")) {
					opmerkingen.add(opm.child("Opmerking").content());
				}
				String reisTip = vertrekkendeTreinXml.child("ReisTip")
						.content();
				vertrekkendeTreinen.add(new VertrekkendeTrein(ritNummer,
						vertrekTijd, vertrekVertraging,
						vertrekVertragingMinuten, vertrekVertragingTekst,
						eindBestemming, treinSoort, routeTekst, vervoerder,
						vertrekSpoor, gewijzigdVertrekspoor, reisTip,
						opmerkingen));
			}
			return new ActueleVertrekTijden(vertrekkendeTreinen);
		} catch (ParseException e) {
			logger.error("Error parsing stream to actuele vertrektijden", e);
			throw new NsApiException(
					"Error parsing stream to actuele vertrektijden", e);
		}
	}

}
