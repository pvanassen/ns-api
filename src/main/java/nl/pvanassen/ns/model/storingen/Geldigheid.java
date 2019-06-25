package nl.pvanassen.ns.model.storingen;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Geldigheid {

    @NotNull
    private final LocalDateTime startDatum;

    @NotNull
    private final LocalDateTime eindDatum;
}
