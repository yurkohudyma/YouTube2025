package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.content.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
