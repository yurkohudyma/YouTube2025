package ua.hudyma.domain.wallet;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.CardType;

import java.time.LocalDate;

@Embeddable
@Data
public class Card {
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private String cardName;
    private String cardNumber;
    private LocalDate issuedOn;
    private LocalDate expiresOn;
}
