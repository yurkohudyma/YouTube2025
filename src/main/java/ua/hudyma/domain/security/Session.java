package ua.hudyma.domain.security;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.OSType;
import ua.hudyma.util.IdGenerator;

@Embeddable
@Data
public class Session {
    private String id = IdGenerator.generateId(2,10);
    @Enumerated(EnumType.STRING)
    private OSType osType;
    private String sessionAgent; //Chrome/FireFox/iOS Acc mgr
}
