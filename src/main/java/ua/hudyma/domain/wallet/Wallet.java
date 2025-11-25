package ua.hudyma.domain.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import ua.hudyma.domain.personal.User;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {
    @Id
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid = UUID.randomUUID();
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "wallet")
    private List<Method> methodList = new ArrayList<>();
    @OneToMany(mappedBy = "wallet")
    private List<Subcription> subcriptionList = new ArrayList<>();
    @OneToMany(mappedBy = "wallet")
    private List<Card> cardList = new ArrayList<>();
}
