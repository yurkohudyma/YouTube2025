package ua.hudyma.domain.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import ua.hudyma.domain.BaseEntity;
import ua.hudyma.domain.personal.User;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data
public class Wallet implements BaseEntity {
    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(
            name = "wallet_payment_methods",
            joinColumns = @JoinColumn(name = "wallet_id"))
    private List<Method> methodList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "wallet_subcriptions",
            joinColumns = @JoinColumn(name = "wallet_id"))
    private List<Subcription> subcriptionList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "wallet_cards",
            joinColumns = @JoinColumn(name = "wallet_id"))
    private List<Card> cardList = new ArrayList<>();
}
