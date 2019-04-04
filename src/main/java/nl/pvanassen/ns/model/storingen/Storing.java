package nl.pvanassen.ns.model.storingen;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * For the exact meaning of all fields, see <a
 * href="http://www.ns.nl/api/api#api-documentatie-storingen-en-werkzaamheden">documentatie storingen en
 * werkzaamheden</a>
 * 
 * @author Paul van Assen
 * 
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Storing implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private final String id;

    @NotNull
    private final String traject;

    @Nullable
    private final String periode;

    @NotNull
    private final String reden;

    @Nullable
    private final String advies;

    @NotNull
    private final String bericht;

    @Nullable
    private final LocalDateTime datum;
}
