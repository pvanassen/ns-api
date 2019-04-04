package nl.pvanassen.ns.model.prijzen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

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

    @NotNull
    private final List<Prijsdeel> prijsdeel;

    @NotNull
    private final BigDecimal totaal;

    @NotNull
    private final Map<String, BigDecimal> korting;
}
