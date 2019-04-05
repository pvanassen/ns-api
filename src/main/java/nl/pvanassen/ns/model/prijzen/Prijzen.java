package nl.pvanassen.ns.model.prijzen;

import java.util.Map;

import nl.pvanassen.ns.model.NsResult;

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
public class Prijzen implements NsResult {

    @NotNull
    private final Map<String, VervoerderKeuze> vervoerderKeuzes;
}