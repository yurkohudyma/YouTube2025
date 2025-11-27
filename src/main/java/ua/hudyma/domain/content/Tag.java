package ua.hudyma.domain.content;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Tag {
    String name;
}
