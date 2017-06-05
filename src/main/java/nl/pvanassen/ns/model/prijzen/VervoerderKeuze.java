package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Created by paul on 5-6-2017.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class VervoerderKeuze {
    private final String naam;
    private final int tariefEenheden;
    private final Map<String, ReisType> reisTypes;
}
