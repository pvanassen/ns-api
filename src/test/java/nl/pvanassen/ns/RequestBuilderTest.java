package nl.pvanassen.ns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.pvanassen.ns.model.prijzen.Producten;
import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheid;
import nl.pvanassen.ns.model.stations.Station;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTrein;

import org.junit.Assert;
import org.junit.Test;

import static nl.pvanassen.ns.ApiRequest.urlEncode;

public class RequestBuilderTest {

    @Test
    public void testGetActueleVertrektijden() {
        ApiRequest<List<VertrekkendeTrein>> request = RequestBuilder.getActueleVertrektijden("Utrecht Centraal");
        Assert.assertEquals("ns-api-avt", request.getPath());
        Assert.assertEquals("station=Utrecht Centraal", request.getRequestString());
    }

    @Test
    public void testGetStations() {
        ApiRequest<List<Station>> request = RequestBuilder.getStations();
        Assert.assertEquals("ns-api-stations-v2", request.getPath());
        Assert.assertEquals("", request.getRequestString());
    }

    @Test
    public void testGetActueleStoringenRequestStation() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen("Utrecht Centraal");
        Assert.assertEquals("ns-api-storingen", request.getPath());
        Assert.assertEquals("station=Utrecht Centraal&", request.getRequestString());
    }

    @Test
    public void testGetGeplandeWerkzaamhedenRequest() {
        ApiRequest<Storingen> request = RequestBuilder.getGeplandeWerkzaamheden();
        Assert.assertEquals("ns-api-storingen", request.getPath());
        Assert.assertEquals("unplanned=true&", request.getRequestString());
    }

    @Test
    public void testGetActueleStoringen() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen();
        Assert.assertEquals("ns-api-storingen", request.getPath());
        Assert.assertEquals("actual=true&", request.getRequestString());
    }

    @Test
    public void testGetReisadviesRequestBuilder() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<List<ReisMogelijkheid>> request = RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht")
                .forArrivalTime(now).viaStation("Hoorn").includeFutureAdvices(4).includePastAdvices(3)
                .userHasNoYearCard().build();
        Assert.assertEquals("ns-api-treinplanner", request.getPath());
        Assert.assertEquals(
                "fromStation=Amsterdam&toStation=Utrecht&viaStation=Hoorn&previousAdvices=3&nextAdvices=4&dateTime="
                        + urlEncode(format.format(now)) + "&departure=false&yearCard=false&", request.getRequestString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetReisadviesRequestBuilderArrivalAndDeparture() {
        Date now = new Date();
        RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").forArrivalTime(now).forDepartureTime(now)
                .build();
    }

    @Test
    public void testGetPrijzenStringString() {
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht");
        Assert.assertEquals("ns-api-prijzen-v2", request.getPath());
        Assert.assertEquals("from=Amsterdam&to=Dortrecht&", request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringString() {
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen");
        Assert.assertEquals("ns-api-prijzen-v2", request.getPath());
        Assert.assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&", request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringDate() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", now);
        Assert.assertEquals("ns-api-prijzen-v2", request.getPath());
        Assert.assertEquals("from=Amsterdam&to=Dortrecht&dateTime=" + format.format(now), request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringStringDate() {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        Date now = new Date();
        ApiRequest<Producten> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen", now);
        Assert.assertEquals("ns-api-prijzen-v2", request.getPath());
        Assert.assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&dateTime=" + format.format(now),
                request.getRequestString());
    }

}
