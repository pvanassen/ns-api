package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;

import org.junit.Test;


public class NsApiTest {

    @Test(expected=NullPointerException.class)
    public void testNsApiNull() {
        new NsApi(null, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNsApiEmpty() {
        new NsApi("", "");
    }

    @Test(expected=NsApiException.class)
    public void testGetApiResponseString() {
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
            @Override
            Class<String> getType() {
                return String.class;
            }
        });
    }

    @Test(expected=NsApiException.class)
    public void testGetApiResponseStations() {
        NsApi nsApi = new NsApi("test", "invalid");
        nsApi.getApiResponse(new StationsRequest());
    }
}
