package nl.pvanassen.ns;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nl.pvanassen.ns.model.prijzen.Prijzen;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class PrijzenRequest extends ApiRequest<Prijzen> {

    private final String from;

    private final String to;

    private final String via;

    private final LocalDateTime dateTime;

    PrijzenRequest(@NotNull final String from, @NotNull final String to, @Nullable final String via, @Nullable final LocalDateTime dateTime) {
        this.from = from;
        this.to = to;
        this.via = via;
        this.dateTime = dateTime;
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getPath()
     */
    @NotNull
    @Override
    String getPath() {
        return "ns-api-prijzen-v3";
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getRequestString()
     */
    @NotNull
    @Override
    String getRequestString() {
        StringBuilder requestString = new StringBuilder();
        requestString.append("from=").append(from).append('&');
        requestString.append("to=").append(to).append('&');
        if (via != null && via.trim().length() != 0) {
            requestString.append("via=").append(via).append('&');
        }
        if (dateTime != null) {
            requestString.append("date=").append(dateTime.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        }
        return requestString.toString();
    }

}
