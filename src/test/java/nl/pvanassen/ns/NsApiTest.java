package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.model.NsResult;
import org.junit.Test;

import java.io.IOException;

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
        nsApi.getApiResponse(new ApiRequest<NsResult>() {

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
