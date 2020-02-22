package nl.pvanassen.ns;

import nl.pvanassen.ns.error.NsApiException;
import nl.pvanassen.ns.model.NsResult;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NsApiTest {

    @Test
    void testNsApiNull() {
        assertThrows(NullPointerException.class, () -> new NsApi(null, null));
    }

    @Test
    void testNsApiEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new NsApi("", ""));
    }

    @Test
    void testGetApiResponseString() throws IOException {
        NsApi nsApi = new NsApi("test", "invalid");
        assertThrows(NsApiException.class, () -> nsApi.getApiResponse(new ApiRequest<NsResult>() {

            @Override
            String getPath() {
                return "";
            }

            @Override
            String getRequestString() {
                return "";
            }
        }));
    }

    @Test
    void testGetApiResponseStations() throws IOException {
        NsApi nsApi = new NsApi("test", "invalid");
        assertThrows(NsApiException.class, () -> nsApi.getApiResponse(new StationsRequest()));
    }
}
