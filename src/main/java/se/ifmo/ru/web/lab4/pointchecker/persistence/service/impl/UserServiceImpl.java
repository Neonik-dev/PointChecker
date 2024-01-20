package se.ifmo.ru.web.lab4.pointchecker.persistence.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.ru.web.lab4.pointchecker.dto.LoginRequest;
import se.ifmo.ru.web.lab4.pointchecker.dto.LoginResponse;
import se.ifmo.ru.web.lab4.pointchecker.dto.RegistrationRequest;
import se.ifmo.ru.web.lab4.pointchecker.exception.AuthException;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;
import se.ifmo.ru.web.lab4.pointchecker.persistence.repository.UserRepository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.UserService;
import se.ifmo.ru.web.lab4.pointchecker.utils.JwtUtil;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCrypt;
    private final EmailServiceImpl emailService;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<User> createUser(User user) {
        // todo вдруг такой юзер уже существует
        User newUser = userRepository.save(user);
        userRepository.flush();
        return Optional.of(newUser);
    }

    @Override
    public User checkByEmailAndPassword(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmailIgnoreCase(loginRequest.email())
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email"));
        if (bCrypt.matches(loginRequest.password(), user.getPassword())) {
            return user;
        }
        throw new UsernameNotFoundException("Invalid password");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            User user = checkByEmailAndPassword(loginRequest);
            String token = jwtUtil.createToken(user);
            return new LoginResponse(token);
        } catch (BadCredentialsException e) {
            throw new AuthException("Registration Error", "User is not registered", "Invalid username or password");
        } catch (Exception e) {
            throw new AuthException("Registration Error", "User is not registered", e.getMessage());
        }
    }

    @Override
    public LoginResponse register(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .email(registrationRequest.email())
                .password(bCryptPasswordEncoder.encode(registrationRequest.password()))
                .isVerified(false)
                .build();

        User newUser = createUser(user).orElseThrow(
                () -> new AuthException("Registration Error", "Invalid user data", "Failed to create a new user in the database")
        );
        emailService.sendSimpleMessage(newUser);

        String token = jwtUtil.createToken(newUser);
        return new LoginResponse(token);
    }
}
