package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.model.NsResult;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class NsApiV1Test {


    @Test(expected = IllegalArgumentException.class)
    public void testNsApiEmpty() {
        new NsApiV1("", "", "");
    }

    @Test(expected = NsApiException.class)
    public void testGetApiResponseString() {
        final NsApi nsApi = new NsApiV1("test", "invalid", "https://null");
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
        NsApi nsApi = new NsApiV1("test", "invalid", "https://null");
        nsApi.getApiResponse(new StationsRequest());
    }
}