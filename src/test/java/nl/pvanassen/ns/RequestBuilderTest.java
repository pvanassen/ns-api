package nl.pvanassen.ns;

import nl.pvanassen.ns.model.prijzen.Prijzen;
import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.vertrektijden.VertrekkendeTreinen;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestBuilderTest {

    @Test
    void testGetActueleVertrektijden() {
        ApiRequest<VertrekkendeTreinen> request = RequestBuilder.getActueleVertrektijden("Utrecht Centraal");
        assertEquals("ns-api-avt", request.getPath());
        assertEquals("station=Utrecht+Centraal", request.getRequestString());
    }

    @Test
    void testGetStations() {
        ApiRequest<Stations> request = RequestBuilder.getStations();
        assertEquals("ns-api-stations-v2", request.getPath());
        assertEquals("", request.getRequestString());
    }

    @Test
    void testGetActueleStoringenRequestStation() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen("Utrecht Centraal");
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("station=Utrecht+Centraal&", request.getRequestString());
    }

    @Test
    void testGetGeplandeWerkzaamhedenRequest() {
        ApiRequest<Storingen> request = RequestBuilder.getGeplandeWerkzaamheden();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("unplanned=true&", request.getRequestString());
    }

    @Test
    void testGetActueleStoringen() {
        ApiRequest<Storingen> request = RequestBuilder.getActueleStoringen();
        assertEquals("ns-api-storingen", request.getPath());
        assertEquals("actual=true&", request.getRequestString());
    }

    @Test
    void testGetReisadviesRequestBuilder() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        ApiRequest<ReisMogelijkheden> request = RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht")
                .forArrivalTime(now).viaStation("Hoorn").includeFutureAdvices(4).includePastAdvices(3)
                .userHasNoYearCard().build();
        assertEquals("ns-api-treinplanner", request.getPath());
        assertEquals(
                "fromStation=Amsterdam&toStation=Utrecht&viaStation=Hoorn&previousAdvices=3&nextAdvices=4&dateTime="
                        + format.format(now) + "&departure=false&yearCard=false&", request.getRequestString());
    }

    @Test
    void testGetReisadviesRequestBuilderArrivalAndDeparture() {
        Date now = new Date();
        assertThrows(IllegalArgumentException.class, () ->
                RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").forArrivalTime(now).forDepartureTime(now)
                        .build());
    }

    @Test
    void testGetPrijzenStringString() {
        ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht");
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&", request.getRequestString());
    }

    @Test
    void testGetPrijzenStringStringString() {
        ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen");
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&", request.getRequestString());
    }

    @Test
    void testGetPrijzenStringStringDate() {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        Date now = new Date();
        ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", now);
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&date=" + format.format(now), request.getRequestString());
    }

    @Test
    void testGetPrijzenStringStringStringDate() {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        Date now = new Date();
        ApiRequest<Prijzen> request = RequestBuilder.getPrijzen("Amsterdam", "Dortrecht", "Groningen", now);
        assertEquals("ns-api-prijzen-v3", request.getPath());
        assertEquals("from=Amsterdam&to=Dortrecht&via=Groningen&date=" + format.format(now),
                request.getRequestString());
    }

}
