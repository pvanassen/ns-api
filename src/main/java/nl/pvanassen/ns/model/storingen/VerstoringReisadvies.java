package nl.pvanassen.ns.model.storingen;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
public class VerstoringReisadvies {

    @NotNull
    private final String titel;

    @NotNull
    private final List<@NotNull String> advies;

}
