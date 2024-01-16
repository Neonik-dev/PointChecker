package se.ifmo.ru.web.lab4.pointchecker.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailIgnoreCase(String email);
}
