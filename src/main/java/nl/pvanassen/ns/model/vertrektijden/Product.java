package nl.pvanassen.ns.model.vertrektijden;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Product {

    private final String number;

    private final String categoryCode;

    private final String shortCategoryName;

    private final String longCategoryName;

    private final String operatorCode;

    private final String operatorName;

    private final String type; // TODO: Enum?
}

/*
          "number": "8824",
          "categoryCode": "IC",
          "shortCategoryName": "NS Intercity",
          "longCategoryName": "Intercity",
          "operatorCode": "NS",
          "operatorName": "NS",
          "type": "TRAIN"

 */