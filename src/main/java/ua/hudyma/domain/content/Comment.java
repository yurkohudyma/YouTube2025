package ua.hudyma.domain.content;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid;
    @Column(columnDefinition = "text")
    private String text;
    @OneToMany(mappedBy = "comment",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Emotion> emotionList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;
}
