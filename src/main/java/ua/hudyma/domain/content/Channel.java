package ua.hudyma.domain.content;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import ua.hudyma.domain.BaseEntity;
import ua.hudyma.domain.personal.User;
import ua.hudyma.util.IdGenerator;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "channels")
@Data
public class Channel implements BaseEntity {
    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid;
    private String channelId = IdGenerator.generateChannelId();
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
    @OneToMany(mappedBy = "channel",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Video> videoList = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "users_subscription_channels",
            joinColumns = @JoinColumn(name = "subscribed_channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscribed_user_id"))
    @ToString.Exclude
    private List<User> subscriberList = new ArrayList<>();
}
