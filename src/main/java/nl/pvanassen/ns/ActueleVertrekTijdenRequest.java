package nl.pvanassen.ns;

import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

public class ActueleVertrekTijdenRequest implements
		ApiRequest<ActueleVertrekTijden> {
	private final String station;

	public ActueleVertrekTijdenRequest(String station) {
		this.station = station;
	}

	@Override
	public String getPath() {
		return "ns-api-avt";
	}

	@Override
	public String getRequestString() {
		return "station=" + station;
	}

	@Override
	public Class<ActueleVertrekTijden> getType() {
		return ActueleVertrekTijden.class;
	}

}
