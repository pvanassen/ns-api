package nl.pvanassen.ns.model.prijzen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
public class ReisKlasse implements Serializable  {
    private final int klasse;
    private final List<Prijsdeel> prijsdeel;
    private final BigDecimal totaal;
    private final Map<String, BigDecimal> korting;
}
