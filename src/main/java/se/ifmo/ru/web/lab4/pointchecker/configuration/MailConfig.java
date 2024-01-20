package se.ifmo.ru.web.lab4.pointchecker.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "spring.mail")
public record MailConfig(
        @NotBlank String host,
        @NotNull int port,
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Properties properties
) {
}