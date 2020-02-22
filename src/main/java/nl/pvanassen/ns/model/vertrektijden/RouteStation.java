package nl.pvanassen.ns.model.vertrektijden;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class RouteStation {

    private final String uicCode;

    private final String mediumName;
}

/*

            "uicCode": "8400053",
            "mediumName": "Alphen a/d Rijn"

 */