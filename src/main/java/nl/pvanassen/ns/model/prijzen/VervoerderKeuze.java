package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by paul on 5-6-2017.
 */
@Builder
@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VervoerderKeuze implements Serializable {
    private final String naam;
    private final int tariefEenheden;
    private final Map<String, ReisType> reisTypes;
}
