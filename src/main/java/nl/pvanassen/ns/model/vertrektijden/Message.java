package nl.pvanassen.ns.model.vertrektijden;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Message {

    private final String message;

    private final String style; // TODO Enum?
}
