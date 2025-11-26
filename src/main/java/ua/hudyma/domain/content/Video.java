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
@Table(name = "videos")
@Data
public class Video implements BaseEntity {
    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid;
    private String videoId = IdGenerator.generateVideoId();
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private Long viewCounter = 0L;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;
    @OneToMany(mappedBy = "video",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "video",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Emotion> emotionList = new ArrayList<>();










    //todo recommendation system for feedline

    //todo купівля чи прокат фільмів
}
