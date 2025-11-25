package ua.hudyma.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {
    MALE ("Чоловік"),
    FEMALE("Жінка");
    private final String value;
}
