package ua.hudyma.domain.wallet;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.PaymentMethodType;

@Embeddable
@Data
public class Method {
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;
}
