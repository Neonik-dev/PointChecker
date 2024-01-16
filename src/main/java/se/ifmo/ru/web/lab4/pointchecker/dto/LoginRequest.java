package se.ifmo.ru.web.lab4.pointchecker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Email
        String email,
        @NotBlank
        @Size(min = 7)
        String password
) {
}
