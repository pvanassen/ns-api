package nl.pvanassen.ns.it;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import nl.pvanassen.ns.ApiRequest;
import nl.pvanassen.ns.NsApi;
import nl.pvanassen.ns.RequestBuilder;
import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class ReisplannerIT {

    @Rule
    public WireMockRule rule = new WireMockRule(options()
            .dynamicPort()
            .usingFilesUnderClasspath("mocks"));

    @Test
    public void validateReisplanner() {
        final NsApi nsApi = new NsApi("api-test", "api-test", rule.baseUrl());
        final ApiRequest<ReisMogelijkheden> request = RequestBuilder.getReisadviesRequestBuilder("Amsterdam", "Utrecht").viaStation("Rotterdam").userHasYearCard().forDepartureTime(LocalDateTime.of(2019, 4, 1, 13, 0, 0)).build();
        final ReisMogelijkheden result = nsApi.getApiResponse(request);
        assertNotNull(result);
        assertEquals(27, result.size());
    }

}
