package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.model.NsResult;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class NsApiTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNsApiEmpty() {
        new NsApi("", "");
    }

    @Test(expected = NsApiException.class)
    public void testGetApiResponseString() {
        final NsApi nsApi = new NsApi("test", "invalid");
        nsApi.getApiResponse(new ApiRequest<NsResult>() {

            @NotNull
            @Override
            String getPath() {
                return "";
            }

            @NotNull
            @Override
            String getRequestString() {
                return "";
            }
        });
    }

    @Test(expected = NsApiException.class)
    public void testGetApiResponseStations() {
        NsApi nsApi = new NsApi("test", "invalid");
        nsApi.getApiResponse(new StationsRequest());
    }
}
