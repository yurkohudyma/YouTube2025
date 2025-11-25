package ua.hudyma.domain.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import ua.hudyma.enums.CardType;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Data
public class Card {
    @Id
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid = UUID.randomUUID();
    private CardType cardType;
    private String cardNumber;
    private LocalDateTime issuedOn;
    private LocalDate expiresOn;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
