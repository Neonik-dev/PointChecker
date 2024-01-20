package se.ifmo.ru.web.lab4.pointchecker.persistence.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.ConfirmationToken;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.ConfirmationTokenService;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String URL_PATH = "/confirm-account?token=";
    private final JavaMailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;
    @Value("${confirmation.url.base}")
    private String baseUrl;

    @Async
    @Override
    public void sendSimpleMessage(User user) {
        ConfirmationToken token = confirmationTokenService.createToken(user);
        String confirmationUrl = baseUrl + URL_PATH + token.getToken();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Complete Registration!");
        message.setText("Hi, " + user.getEmail() + ", to confirm your account, please click here: " + confirmationUrl);
        emailSender.send(message);
    }
}