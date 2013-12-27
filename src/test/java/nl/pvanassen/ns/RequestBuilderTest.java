package nl.pvanassen.ns;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;

import org.junit.Test;


public class RequestBuilderTest {

    @Test
    public void testGetActueleVertrektijden() {
        ApiRequest<ActueleVertrekTijden> request = RequestBuilder.getActueleVertrektijden("Utrecht Centraal");
        assertEquals("ns-api-avt", request.getPath());
        assertEquals("station=Utrecht Centraal", request.getRequestString());
        assertEquals(ActueleVertrekTijden.class, request.getType());
    }

    @Test
    public void testGetStations() {
        ApiRequest<Stations> request = RequestBuilder.getStations();
        assertEquals("ns-api-stations-v2", request.getPath());
        assertEquals("", request.getRequestString());
        assertEquals(Stations.class, request.getType());
    }

    @Test
    public void testGetActueleStoringenRequestStation() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen("Utrecht Centraal");
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("station=Utrecht Centraal&", request.getRequestString());
        assertEquals(Storingen.class, request.getType());
    }

    @Test
    public void testGetGeplandeWerkzaamhedenRequest() {
        ApiRequest<Storingen> request = RequestBuilder.getGeplandeWerkzaamheden();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("unplanned=true&", request.getRequestString());
        assertEquals(Storingen.class, request.getType());
    }

    @Test
    public void testGetActueleStoringen() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("actual=true&", request.getRequestString());
        assertEquals(Storingen.class, request.getType());
    }

    @Test
    public void testGetReisadviesRequestBuilder() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<ReisMogelijkheden> request = RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").forArivalTime(now).build();
        assertEquals("ns-api-treinplanner", request.getPath());
        assertEquals("fromStation=Amsterdam&toStation=Utrecht&dateTime=" + format.format(now) + "&departure=false&", request.getRequestString());
        assertEquals(ReisMogelijkheden.class, request.getType());
    }

}
