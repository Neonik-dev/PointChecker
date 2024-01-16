package se.ifmo.ru.web.lab4.pointchecker.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.ru.web.lab4.pointchecker.dto.LoginRequest;
import se.ifmo.ru.web.lab4.pointchecker.dto.LoginResponse;
import se.ifmo.ru.web.lab4.pointchecker.dto.RegistrationRequest;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping(value = "/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping(value = "/signup")
    public LoginResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.register(registrationRequest);
    }
}
