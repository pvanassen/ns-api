package nl.pvanassen.ns.model.storingen;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
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
