package nl.pvanassen.ns.parser;

import org.jetbrains.annotations.NotNull;

public interface ResponsePresent<T extends Response> extends Response<T> {

    /**
     * @return Content of the element
     */
    @NotNull
    String content();

}
