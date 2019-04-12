package nl.pvanassen.ns;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.model.NsResult;
import nl.pvanassen.ns.model.prijzen.PrijsHandle;
import nl.pvanassen.ns.model.reisadvies.ReisadviesHandle;
import nl.pvanassen.ns.model.stations.StationsHandle;
import nl.pvanassen.ns.model.storingen.StoringenHandle;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijdenHandle;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class NsApiV2 extends NsApi {

    private final HttpConnection httpConnection;

    private final String baseUrl;

    private final Map<Class<?>, Handle<?>> handleMap = new HashMap<>();

    NsApiV2(@NotNull final String username, @NotNull final String password, @NotNull final String baseUrl) {
        // No isEmpty to remain compatible with JDK 1.5
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        httpConnection = new HttpConnection(username, password);
        handleMap.put(ActueleVertrekTijdenRequest.class, new ActueleVertrekTijdenHandle());
        handleMap.put(StationsRequest.class, new StationsHandle());
        handleMap.put(StoringenEnWerkzaamhedenRequest.class, new StoringenHandle());
        handleMap.put(ReisadviesRequest.class, new ReisadviesHandle());
        handleMap.put(PrijzenRequest.class, new PrijsHandle());
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl;
        }
        else {
            this.baseUrl = baseUrl.concat("/");
        }
    }

    @Override
    protected String getBaseUrl() {
        return baseUrl;
    }

    @Override
    protected HttpConnection getHttpConnection() {
        return httpConnection;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends NsResult> Handle<T> getHandle(Class<?> requestClass) {
        return (Handle<T>) handleMap.get(requestClass);
    }
}
