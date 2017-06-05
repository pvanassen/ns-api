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
public class Prijzen {
    private final Map<String, VervoerderKeuze> vervoerderKeuzes;
}
