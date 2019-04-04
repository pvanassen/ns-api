package nl.pvanassen.ns;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nl.pvanassen.ns.model.reisadvies.ReisMogelijkheden;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ReisadviesRequest extends ApiRequest<ReisMogelijkheden> {

    @NotNull
    private final String fromStation;

    @NotNull
    private final String toStation;

    @Nullable
    private final String viaStation;

    @Nullable
    private final Integer previousAdvices;

    @Nullable
    private final Integer nextAdvices;

    @Nullable
    private final LocalDateTime  dateTime;

    @Nullable
    private final Boolean departure;

    @Nullable
    private final Boolean hslAllowed;

    @Nullable
    private final Boolean yearCard;

    ReisadviesRequest(@NotNull final String fromStation, @NotNull final String toStation, @Nullable final String viaStation, @Nullable final Integer previousAdvices,
            @Nullable final Integer nextAdvices, @Nullable final LocalDateTime dateTime, @Nullable final Boolean departure, @Nullable final Boolean hslAllowed,
            @Nullable final Boolean yearCard) {
        super();
        this.fromStation = UrlParamHelper.encode(fromStation);
        this.toStation = UrlParamHelper.encode(toStation);
        this.viaStation = UrlParamHelper.encode(viaStation);
        this.previousAdvices = previousAdvices;
        this.nextAdvices = nextAdvices;
        this.dateTime = dateTime;
        this.departure = departure;
        this.hslAllowed = hslAllowed;
        this.yearCard = yearCard;
    }

    @NotNull
    @Override
    String getPath() {
        return "ns-api-treinplanner";
    }

    @NotNull
    @Override
    String getRequestString() {
        final StringBuilder requestString = new StringBuilder();
        requestString.append("fromStation=").append(fromStation).append('&');
        requestString.append("toStation=").append(toStation).append('&');
        if (viaStation != null && viaStation.trim().length() != 0) {
            requestString.append("viaStation=").append(viaStation).append('&');
        }
        if (previousAdvices != null) {
            requestString.append("previousAdvices=").append(previousAdvices).append('&');
        }
        if (nextAdvices != null) {
            requestString.append("nextAdvices=").append(nextAdvices).append('&');
        }
        if (dateTime != null) {
            requestString.append("dateTime=")
                    .append(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                    .append('&');
        }
        if (departure != null) {
            requestString.append("departure=").append(departure).append('&');
        }
        if (hslAllowed != null) {
            requestString.append("hslAllowed=").append(hslAllowed).append('&');
        }
        if (yearCard != null) {
            requestString.append("yearCard=").append(yearCard).append('&');
        }
        return requestString.toString();
    }

}
