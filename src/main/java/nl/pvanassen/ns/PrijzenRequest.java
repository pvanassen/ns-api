package nl.pvanassen.ns;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.pvanassen.ns.model.prijzen.Producten;


class PrijzenRequest extends ApiRequest<Producten> {
    private final String from;
    private final String to;
    private final String via;
    private final Date dateTime;

    PrijzenRequest(String from, String to, String via, Date dateTime) {
        this.from = from;
        this.to = to;
        this.via = via;
        this.dateTime = dateTime;
    }

    @Override
    String getPath() {
        return "ns-api-prijzen-v2";
    }

    @Override
    String getRequestString() {
        StringBuilder requestString = new StringBuilder();
        requestString.append("from=").append(from).append('&');
        requestString.append("to=").append(to).append('&');
        if (via != null && via.trim().length() != 0) {
            requestString.append("via=").append(via).append('&');
        }
        if (dateTime != null) {
            requestString.append("dateTime=").append(new SimpleDateFormat(NsApi.DATETIME_FORMAT).format(dateTime));
        }
        return requestString.toString();
    }

    @Override
    Class<Producten> getType() {
        return Producten.class;
    }

}
