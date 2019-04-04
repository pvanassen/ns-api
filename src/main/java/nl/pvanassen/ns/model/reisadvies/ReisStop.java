package nl.pvanassen.ns.model.reisadvies;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * http://www.ns.nl/api/api#api-documentatie-reisadviezen
 * 
 * @author Paul van Assen
 * 
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReisStop implements Serializable {

    @NotNull
    private final String naam;

    @NotNull
    private final LocalDateTime tijd;

    @Nullable
    private final String spoor;

    private final boolean gewijzigdVertrekspoor;
}
