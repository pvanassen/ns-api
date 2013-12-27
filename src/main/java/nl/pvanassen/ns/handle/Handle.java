package nl.pvanassen.ns.handle;

import java.io.InputStream;

public interface Handle<T> {

    T getModel(InputStream stream);
}
