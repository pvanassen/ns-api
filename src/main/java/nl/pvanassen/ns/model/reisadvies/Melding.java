package nl.pvanassen.ns.model.reisadvies;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

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
public class Melding implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private final String id;

    private final boolean ernstig;

    @NotNull
    private final String text;
}
