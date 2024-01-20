package se.ifmo.ru.web.lab4.pointchecker.persistence.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.ConfirmationToken;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;
import se.ifmo.ru.web.lab4.pointchecker.persistence.repository.ConfirmationTokenRepository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.repository.UserRepository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.ConfirmationTokenService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Value("${confirmation.expiration}")
    private Long expiration;

    @Override
    public ConfirmationToken createToken(User user) {
        ConfirmationToken token = ConfirmationToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expirationDate(new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(expiration)))
                .build();

        ConfirmationToken newToke = confirmationTokenRepository.save(token);
        confirmationTokenRepository.flush();
        return newToke;
    }

    @Override
    public String checkConfirmationToken(String confirmationToken) {
        Optional<ConfirmationToken> token = confirmationTokenRepository.
                findByTokenAndExpirationDateAfter(confirmationToken, new Date());

        if (token.isPresent()) {
            User user = userRepository.findUserByEmailIgnoreCase(
                    token.get().getUser().getEmail()
            ).orElseThrow(
                    () -> new UsernameNotFoundException("No user found with email")
            );
            user.setIsVerified(true);
            userRepository.save(user);
            return "Your account verified!";
        } else {
            return "The link is invalid or broken!";
        }
    }
}