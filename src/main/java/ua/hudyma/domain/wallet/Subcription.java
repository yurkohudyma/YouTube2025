package ua.hudyma.domain.wallet;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.SubcriptionType;

import java.math.BigDecimal;

@Embeddable
@Data
public class Subcription {
    @Enumerated(EnumType.STRING)
    private SubcriptionType subcriptionType;
    private BigDecimal price;
}
