package nl.pvanassen.ns.model.prijzen;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by paul on 5-6-2017.
 */
@Builder
@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Prijsdeel implements Serializable {
    private final String vervoerder;
    private final BigDecimal prijs;
    private final String van;
    private final String naar;
}
