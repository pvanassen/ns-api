package nl.pvanassen.ns;

import nl.pvanassen.ns.handle.Handle;
import nl.pvanassen.ns.model.NsResult;
import nl.pvanassen.ns.model.prijzen.PrijsHandleV1;
import nl.pvanassen.ns.model.reisadvies.ReisadviesHandleV1;
import nl.pvanassen.ns.model.stations.StationsHandleV1;
import nl.pvanassen.ns.model.storingen.StoringenHandleV1;
import nl.pvanassen.ns.model.vertrektijden.ActueleVertrekTijdenHandleV1;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class NsApiV1 extends NsApi {

    private final HttpConnection httpConnection;

    private final String baseUrl;

    private final Map<Class<?>, Handle<?>> handleMap = new HashMap<>();

    NsApiV1(@NotNull final String username, @NotNull final String password, @NotNull final String baseUrl) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        httpConnection = new HttpConnection(username, password);
        handleMap.put(ActueleVertrekTijdenRequest.class, new ActueleVertrekTijdenHandleV1());
        handleMap.put(StationsRequest.class, new StationsHandleV1());
        handleMap.put(StoringenEnWerkzaamhedenRequest.class, new StoringenHandleV1());
        handleMap.put(ReisadviesRequest.class, new ReisadviesHandleV1());
        handleMap.put(PrijzenRequest.class, new PrijsHandleV1());
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
