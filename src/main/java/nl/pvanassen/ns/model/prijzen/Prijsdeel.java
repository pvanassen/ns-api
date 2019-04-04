package nl.pvanassen.ns.model.prijzen;

import java.io.Serializable;
import java.math.BigDecimal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    private final String vervoerder;

    @NotNull
    private final BigDecimal prijs;

    @NotNull
    private final String van;

    @NotNull
    private final String naar;
}
