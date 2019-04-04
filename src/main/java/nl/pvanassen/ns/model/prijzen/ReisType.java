package nl.pvanassen.ns.model.prijzen;

import java.io.Serializable;
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
public class ReisType implements Serializable {

    @NotNull
    private final String naam;

    @NotNull
    private final Map<Integer, ReisKlasse> reisKlassen;
}
