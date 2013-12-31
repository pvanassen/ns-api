package nl.pvanassen.ns;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.pvanassen.ns.model.prijzen.Producten;
import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;
import nl.pvanassen.ns.model.stations.Station;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

import org.junit.Test;


public class RequestBuilderTest {

    @Test
    public void testGetActueleVertrektijden() {
        ApiRequest<List<VertrekkendeTrein>> request = RequestBuilder.getActueleVertrektijden("Utrecht Centraal");
        assertEquals("ns-api-avt", request.getPath());
        assertEquals("station=Utrecht Centraal", request.getRequestString());
    }

    @Test
    public void testGetStations() {
        ApiRequest<List<Station>> request = RequestBuilder.getStations();
        assertEquals("ns-api-stations-v2", request.getPath());
        assertEquals("", request.getRequestString());
    }

    @Test
    public void testGetActueleStoringenRequestStation() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen("Utrecht Centraal");
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("station=Utrecht Centraal&", request.getRequestString());
    }

    @Test
    public void testGetGeplandeWerkzaamhedenRequest() {
        ApiRequest<Storingen> request = RequestBuilder.getGeplandeWerkzaamheden();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("unplanned=true&", request.getRequestString());
    }

    @Test
    public void testGetActueleStoringen() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("actual=true&", request.getRequestString());
    }

    @Test
    public void testGetReisadviesRequestBuilder() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<ReisMogelijkheden> request = RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").forArrivalTime(now).viaStation("Hoorn").includeFutureAdvices(4).includePastAdvices(3).userHasNoYearCard().build();
        assertEquals("ns-api-treinplanner", request.getPath());
        assertEquals("fromStation=Amsterdam&toStation=Utrecht&viaStation=Hoorn&previousAdvices=3&nextAdvices=4&dateTime=" + format.format(now) + "&departure=false&yearCard=false&", request.getRequestString());
    }


    @Test(expected=IllegalArgumentException.class)
    public void testGetReisadviesRequestBuilderArrivalAndDeparture() {
        Date now = new Date();
        RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").forArrivalTime(now).forDepartureTime(now).build();
    }
    
    @Test
    public void testGetPrijzenStringString() {
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht");
        assertEquals("ns-api-prijzen-v2", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&", request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringString() {
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen");
        assertEquals("ns-api-prijzen-v2", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&", request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringDate() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", now);
        assertEquals("ns-api-prijzen-v2", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&dateTime=" + format.format(now), request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringStringDate() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen", now);
        assertEquals("ns-api-prijzen-v2", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&dateTime=" + format.format(now), request.getRequestString());
    }

}
