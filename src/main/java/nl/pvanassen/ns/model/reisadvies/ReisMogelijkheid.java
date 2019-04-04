package nl.pvanassen.ns.model.reisadvies;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class ReisMogelijkheid implements Serializable {

    @NotNull
    private final List<@NotNull Melding> meldingen;

    private final int aantalOverstappen;

    private final int geplandeReisTijdMinuten;

    private final int actueleReisTijdMinuten;

    @Nullable
    private final String aankomstVertraging;

    private final boolean optimaal;

    @NotNull
    private final LocalDateTime geplandeVertrekTijd;

    @NotNull
    private final LocalDateTime actueleVertrekTijd;

    @NotNull
    private final LocalDateTime geplandeAankomstTijd;

    @NotNull
    private final LocalDateTime actueleAankomstTijd;

    @Nullable
    private final String status;

    @NotNull
    private final List<@NotNull ReisDeel> reisDelen;
}
