package nl.pvanassen.ns.model.storingen;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
public class Geldigheid {

    @NotNull
    private final LocalDateTime startDatum;

    @NotNull
    private final LocalDateTime eindDatum;

    @NotNull
    private final String startTijd;

    @NotNull
    private final String eindTijd;
}
