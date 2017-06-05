package nl.pvanassen.ns.model.prijzen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Created by paul on 5-6-2017.
 */
@Data
@AllArgsConstructor
public class ReisType {
    private final String naam;
    private final Map<Integer, ReisKlasse> reisKlassen;
}
