package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User implements BaseEntity {
    @Id
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid = UUID.randomUUID();
    @Embedded
    private Profile profile;

}
