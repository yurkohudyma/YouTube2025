package ua.hudyma.domain.personal;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import ua.hudyma.domain.security.Device;
import ua.hudyma.domain.wallet.Wallet;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
    @ElementCollection
    @CollectionTable(
            name = "user_devices",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Device> deviceList = new ArrayList<>();
    @OneToOne(mappedBy = "user")
    private Wallet wallet;


}
