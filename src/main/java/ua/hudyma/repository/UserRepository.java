package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
