package nl.pvanassen.ns;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nl.pvanassen.ns.model.prijzen.Prijzen;
import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTreinen;

import org.junit.Test;

public class RequestBuilderTest {

    @Test
    public void testGetActueleVertrektijden() {
        final ApiRequest<VertrekkendeTreinen> request = RequestBuilder.getActueleVertrektijden("Utrecht Centraal");
        assertEquals("ns-api-avt", request.getPath());
        assertEquals("station=Utrecht+Centraal", request.getRequestString());
    }

    @Test
    public void testGetStations() {
        final ApiRequest<Stations> request = RequestBuilder.getStations();
        assertEquals("ns-api-stations-v2", request.getPath());
        assertEquals("", request.getRequestString());
    }

    @Test
    public void testGetActueleStoringenRequestStation() {
        final ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen("Utrecht Centraal");
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("station=Utrecht+Centraal&", request.getRequestString());
    }

    @Test
    public void testGetGeplandeWerkzaamhedenRequest() {
        final ApiRequest<Storingen> request = RequestBuilder.getGeplandeWerkzaamheden();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("unplanned=true&", request.getRequestString());
    }

    @Test
    public void testGetActueleStoringen() {
        final ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("actual=true&", request.getRequestString());
    }

    @Test
    public void testGetReisadviesRequestBuilder() {
        final LocalDateTime now = now();
        final ApiRequest<ReisMogelijkheden> request = RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht")
                .forArrivalTime(now)
                .viaStation("Hoorn")
                .includeFutureAdvices(4)
                .includePastAdvices(3)
                .userHasNoYearCard()
                .build();

        assertEquals("ns-api-treinplanner", request.getPath());
        assertEquals("fromStation=Amsterdam&toStation=Utrecht&viaStation=Hoorn&previousAdvices=3&nextAdvices=4&dateTime=" +
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + "&departure=false&yearCard=false&", request.getRequestString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetReisadviesRequestBuilderArrivalAndDeparture() {
        final LocalDateTime now = now();
        RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").forArrivalTime(now).forDepartureTime(now)
                .build();
    }

    @Test
    public void testGetPrijzenStringString() {
        final ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht");
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&", request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringString() {
        final ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen");
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&", request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringDate() {
        final LocalDateTime now = now();
        final ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", now);
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&date=" + now.format(DateTimeFormatter.ofPattern("ddMMyyyy")), request.getRequestString());
    }

    @Test
    public void testGetPrijzenStringStringStringDate() {
        final LocalDateTime now = now();
        final ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen", now);
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&date=" + now.format(DateTimeFormatter.ofPattern("ddMMyyyy")),
                request.getRequestString());
    }

}
