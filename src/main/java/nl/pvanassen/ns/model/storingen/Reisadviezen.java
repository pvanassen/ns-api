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
public class Reisadviezen {

    @NotNull
    private final String titel;

    @NotNull
    private final List<@NotNull VerstoringReisadvies> verstoringreisadvies;
}
