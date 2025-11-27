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
@Table(name = "posts")
@Data
public class Post implements BaseEntity {
    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.BINARY)
    private UUID uuid;
    private String name;
    private String postId = IdGenerator.generateId(2,7);
    @Column(columnDefinition = "text")
    private String text;
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Emotion> emotionList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

}
