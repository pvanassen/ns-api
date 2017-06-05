package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import nl.pvanassen.ns.model.NsResult;

import java.util.Map;

/**
 * Created by paul on 5-6-2017.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Prijzen implements NsResult {
    private final Map<String, VervoerderKeuze> vervoerderKeuzes;
}
