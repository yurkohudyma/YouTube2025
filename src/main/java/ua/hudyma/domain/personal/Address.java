package ua.hudyma.domain.personal;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.AddressType;

@Embeddable
@Data
public class Address {
    private String address;
    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;
}
