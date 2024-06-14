package se.ifmo.ru.web.lab4.pointchecker.utils;

import org.junit.jupiter.api.Test;
import se.ifmo.ru.web.lab4.pointchecker.configuration.JwtConfig;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {
    @Test
    void createToken() {
        User user = new User(3L, "forolimpvk@gmail.com", "12345", OffsetDateTime.now(), false);
        JwtConfig jwtConfig = new JwtConfig("123", 3L);
        JwtUtil jwtUtil = new JwtUtil(jwtConfig);
        assertThrows(IllegalArgumentException.class, () -> jwtUtil.createToken(user));
    }
}
