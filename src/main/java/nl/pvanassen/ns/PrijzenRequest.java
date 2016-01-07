package nl.pvanassen.ns;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.pvanassen.ns.model.prijzen.Producten;

class PrijzenRequest extends ApiRequest<Producten> {
    private final String from;
    private final String to;
    private final String via;
    private final String dateTime;

    PrijzenRequest(String from, String to, String via, Date dateTime) {
        this.from = UrlParamHelper.encode(from);
        this.to = UrlParamHelper.encode(to);
        this.via = UrlParamHelper.encode(via);
        this.dateTime = UrlParamHelper.formatDate(dateTime);
    }

    /**
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getPath()
     */
    @Override
    String getPath() {
        return "ns-api-prijzen-v2";
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see nl.pvanassen.ns.ApiRequest#getRequestString()
     */
    @Override
    String getRequestString() {
        StringBuilder requestString = new StringBuilder();
        requestString.append("from=").append(from).append('&');
        requestString.append("to=").append(to).append('&');
        if (via != null && via.trim().length() != 0) {
            requestString.append("via=").append(via).append('&');
        }
        if (dateTime != null) {
            requestString.append("dateTime=").append(dateTime);
        }
        return requestString.toString();
    }

}
