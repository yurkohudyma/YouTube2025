package ua.hudyma.domain.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "payment_methods")
@Data
public class Method {
    @Id
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
