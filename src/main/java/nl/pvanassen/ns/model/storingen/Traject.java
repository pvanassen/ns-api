package nl.pvanassen.ns.model.storingen;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Traject {

    @NotNull
    private final List<String> stations;

    @NotNull
    private final LocalDateTime beginTijd;

    @NotNull
    private final LocalDateTime eindTijd;
}
