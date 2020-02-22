package nl.pvanassen.ns.model.vertrektijden;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.pvanassen.ns.model.NsResult;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Departure implements NsResult {

    @NotNull
    private final String direction;

    @NotNull
    private final String name;

    @NotNull
    private final LocalDateTime plannedDateTime;

    @NotNull
    private final LocalDateTime actualDateTime;

    @NotNull
    private final String plannedTrack;

    @NotNull
    private final Product product;

    @NotNull
    private final String trainCategory;

    private final boolean cancelled;

    @NotNull
    private final List<@NotNull RouteStation> routeStations;

    @NotNull
    private final List<@NotNull Message> messages;

    @NotNull
    private final String departureStatus; // TODO: Enum?
}
