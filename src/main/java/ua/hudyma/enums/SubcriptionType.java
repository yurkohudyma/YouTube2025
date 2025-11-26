package ua.hudyma.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubcriptionType {
    PREMIUM ("Преміум"),
    FAMILY("Сімейний план");
    private final String value;
}
