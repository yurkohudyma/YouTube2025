package ua.hudyma.domain.personal;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.EmailType;
import ua.hudyma.enums.PhoneType;

@Embeddable
@Data
public class Email {
    private String email;
    @Enumerated(value = EnumType.STRING)
    private EmailType emailType;
}
