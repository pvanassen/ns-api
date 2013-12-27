package nl.pvanassen.ns;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.http.HttpConnection;
import nl.pvanassen.ns.model.stations.Stations;
import nl.pvanassen.ns.model.stations.StationsHandle;
import nl.pvanassen.ns.model.storingen.Storingen;
import nl.pvanassen.ns.model.storingen.StoringenHandle;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijden;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijdenHandle;

import org.apache.commons.io.IOUtils;

/**
 * Starting point for using the NS api
 * 
 * @author Paul van Assen
 * 
 */
public class NsApi {

    private final HttpConnection httpConnection;

    private static final String BASE_URL = "http://webservices.ns.nl/";

    private final Map<Class<?>, Handle<?>> handleMap = new HashMap<Class<?>, Handle<?>>();

    public NsApi(String username, String password) {
        httpConnection = new HttpConnection(username, password);
        handleMap.put(ActueleVertrekTijden.class, new ActueleVertrekTijdenHandle());
        handleMap.put(Stations.class, new StationsHandle());
        handleMap.put(Storingen.class, new StoringenHandle());
    }

    public <T> T getApiResponse(ApiRequest<T> request) {
        InputStream stream = null;
        try {
            stream = httpConnection.getContent(NsApi.BASE_URL + request.getPath() + "?" + request.getRequestString());
            @SuppressWarnings("unchecked")
            Handle<T> handle = (Handle<T>) handleMap.get(request.getType());
            return handle.getModel(stream);
        }
        finally {
            IOUtils.closeQuietly(stream);
        }
    }

}
