package ua.hudyma.domain.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Data
@Entity
@Table(name = "subscriptions")
public class Subcription {
    @Id
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
