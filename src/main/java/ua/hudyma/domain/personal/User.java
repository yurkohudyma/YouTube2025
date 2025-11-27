package ua.hudyma.domain.personal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import ua.hudyma.domain.BaseEntity;
import ua.hudyma.domain.content.*;
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
    @UuidGenerator
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid;
    @Embedded
    private Profile profile;
    @ElementCollection
    @CollectionTable(
            name = "user_devices",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Device> deviceList = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Channel> channelList = new ArrayList<>();
    @ManyToMany(mappedBy = "subscriberList")
    @ToString.Exclude
    private List<Channel> subscribedChannelList = new ArrayList<>();
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Emotion> emotionList = new ArrayList<>();
    @ManyToMany(mappedBy = "purchaserUserList")
    @ToString.Exclude
    private List<Video> purchasedVideoList = new ArrayList<>();
    @ManyToMany(mappedBy = "renterUserList")
    @ToString.Exclude
    private List<Video> rentedVideoList = new ArrayList<>();
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Post> postList = new ArrayList<>();

}
