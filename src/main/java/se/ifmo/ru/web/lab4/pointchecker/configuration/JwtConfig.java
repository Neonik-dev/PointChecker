package se.ifmo.ru.web.lab4.pointchecker.configuration;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(
        @NotBlank String secret,
        long exp
) {
}
