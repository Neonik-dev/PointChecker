package se.ifmo.ru.web.lab4.pointchecker.persistence.service;

import se.ifmo.ru.web.lab4.pointchecker.dto.LoginRequest;
import se.ifmo.ru.web.lab4.pointchecker.dto.LoginResponse;
import se.ifmo.ru.web.lab4.pointchecker.dto.RegistrationRequest;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

import java.util.Optional;

public interface UserService {
    User getUserByEmail(String email);

    Optional<User> createUser(User user);

    User checkByEmailAndPassword(LoginRequest loginRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(RegistrationRequest registrationRequest);
}
