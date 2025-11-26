package ua.hudyma.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentMethodType {
    BY_CARD ("Картка"),
    BANK_TRANSFER ("Банківський переказ"),
    TEST_PAYMENT ("Тестовий платіж"),
    FREE_OF_CHARGE("Безкоштовно");
    private final String value;
}
