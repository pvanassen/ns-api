package nl.pvanassen.ns.model.storingen;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class BaanvakBeperking {

    @NotNull
    private final String van;

    @NotNull
    private final String tot;

    @NotNull
    private final List<String> via;

    @NotNull
    private final Richting richting;

}
