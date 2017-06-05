package nl.pvanassen.ns.model.prijzen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by paul on 5-6-2017.
 */
@Data
@AllArgsConstructor
public class ReisKlasse implements Serializable  {
    private final int klasse;
    private final List<Prijsdeel> prijsdeel;
    private final BigDecimal totaal;
    private final Map<String, BigDecimal> korting;
}
