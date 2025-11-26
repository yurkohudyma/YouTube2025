package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.content.Emotion;

import java.util.UUID;

public interface EmotionRepository extends JpaRepository<Emotion, UUID> {
}
