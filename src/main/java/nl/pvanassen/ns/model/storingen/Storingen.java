package nl.pvanassen.ns.model.storingen;

import java.util.List;

import nl.pvanassen.ns.model.NsResult;

import org.jetbrains.annotations.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * http://www.ns.nl/reisinformatie/ns-api/documentatie-storingen-en-werkzaamheden.html
 * 
 * @author Paul van Assen
 * 
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Storingen implements NsResult {

    @NotNull
    private final List<@NotNull Storing> ongeplandeStoringen;

    @NotNull
    private final List<@NotNull Storing> geplandeStoringen;
}
