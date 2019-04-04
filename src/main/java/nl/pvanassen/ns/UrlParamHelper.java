package nl.pvanassen.ns;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.jetbrains.annotations.Nullable;

/**
 * @author Paul van Assen
 */
class UrlParamHelper {

    private final Charset charset;

    private static final UrlParamHelper INSTANCE = new UrlParamHelper();

    private UrlParamHelper() {
        if (!Charset.isSupported("UTF-8")) {
            throw new IllegalArgumentException("UTF-8 encoding not supported by platform");
        }
        charset = Charset.forName("UTF-8");
    }

    static String encode(@Nullable final String param) {
        // Keep null null
        if (param == null) {
            return null;
        }
        try {
            return URLEncoder.encode(param, INSTANCE.charset.name());
        }
        catch (UnsupportedEncodingException e) {
            // This has been checked on class setup.
            throw new IllegalArgumentException("UTF-8 encoding not supported by platform", e);
        }
    }
}
