package nl.pvanassen.ns;

import java.io.IOException;

import nl.pvanassen.ns.error.NsApiException;

import org.junit.Test;

public class NsApiTest {

    @Test(expected = NullPointerException.class)
    public void testNsApiNull() {
        new NsApi(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNsApiEmpty() {
        new NsApi("", "");
    }

    @Test(expected = NsApiException.class)
    public void testGetApiResponseString() throws IOException {
        NsApi nsApi = new NsApi("test", "invalid");
        nsApi.getApiResponse(new ApiRequest<String>() {

            @Override
            String getPath() {
                return "";
            }

            @Override
            String getRequestString() {
                return "";
            }
        });
    }

    @Test(expected = NsApiException.class)
    public void testGetApiResponseStations() throws IOException {
        NsApi nsApi = new NsApi("test", "invalid");
        nsApi.getApiResponse(new StationsRequest());
    }
}
