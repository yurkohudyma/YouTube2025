package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.content.Channel;
import ua.hudyma.domain.content.Video;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {
}
