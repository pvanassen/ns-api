package nl.pvanassen.ns.model.prijzen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by paul on 5-6-2017.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Prijsdeel {
    private final String vervoerder;
    private final BigDecimal prijs;
    private final String van;
    private final String naar;
}
