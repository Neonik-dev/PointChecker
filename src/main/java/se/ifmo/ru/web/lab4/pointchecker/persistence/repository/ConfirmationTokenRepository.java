package se.ifmo.ru.web.lab4.pointchecker.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.ConfirmationToken;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByTokenAndExpirationDateAfter(String token, Date expiration);
}
