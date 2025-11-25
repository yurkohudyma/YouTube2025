package ua.hudyma.domain.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ua.hudyma.enums.DeviceType;

import java.util.List;
import java.util.Map;

@Embeddable
@Data
public class Device {
    private String deviceName;
    @Enumerated(EnumType.STRING)
    public DeviceType deviceType;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> sessionList;
}
